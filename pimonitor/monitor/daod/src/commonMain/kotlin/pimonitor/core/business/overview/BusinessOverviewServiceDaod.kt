package pimonitor.core.business.overview

import akkounts.containsAny
import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.reports.utils.CategoryEntry
import bitframe.core.RequestBody
import bitframe.core.get
import bitframe.core.isEqualTo
import datetime.toLocalDate
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later
import pimonitor.core.business.utils.info.LoadInfoParsedParams
import pimonitor.core.businesses.DASHBOARD_FINANCIAL
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.configs.MonitorServiceConfigDaod
import pimonitor.core.picortex.PiCortexApiCredentials
import pimonitor.core.sage.SageApiCredentials
import pimonitor.core.sage.toCompany
import presenters.changes.ChangeFeeling
import presenters.changes.MoneyChangeBox
import presenters.changes.moneyChangeBoxOf
import presenters.charts.Chart

class BusinessOverviewServiceDaod(
    val config: MonitorServiceConfigDaod
) : BusinessOverviewServiceCore {
    private val factory get() = config.daoFactory
    private val monitoredBusinessDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val piCortexApiCredentialsDao by lazy { factory.get<PiCortexApiCredentials>() }
    private val sageApiCredentialsDao by lazy { factory.get<SageApiCredentials>() }
    private val sage by lazy { config.sage }
    private val picortex by lazy { config.picortex }

    override fun load(rb: RequestBody.Authorized<LoadInfoParsedParams>): Later<MonitoredBusinessOverview> = config.scope.later {
        val params = rb.data
        val business = monitoredBusinessDao.load(params.businessId).await()
        val operational = business.operationalBoard
        val financial = business.financialBoard
        var overview: MonitoredBusinessOverview = MonitoredBusinessOverview.MissingData(business)
        var operationalProviderError: String? = null
        var financialProviderError: String? = null
        if (operational != DASHBOARD_OPERATIONAL.NONE) {
            overview += try {
                operationalOverview(business, params)
            } catch (e: Throwable) {
                operationalProviderError = e.message
                overview
            }
        }
        if (financial != DASHBOARD_FINANCIAL.NONE) {
            overview += try {
                financialOverview(business, params)
            } catch (e: Throwable) {
                financialProviderError = e.message
                overview
            }
        }
        when (val o = overview) {
            is MonitoredBusinessOverview.FinancialAndOperational -> o
            is MonitoredBusinessOverview.FinancialOnly -> if (operationalProviderError != null) {
                o.copy(missingOperationalReason = MonitoredBusinessOverview.Reason.ProviderError(operationalProviderError))
            } else {
                o.copy(missingOperationalReason = MonitoredBusinessOverview.Reason.NotIntegrated)
            }
            is MonitoredBusinessOverview.OperationalOnly -> if (financialProviderError != null) {
                o.copy(missingFinancialReason = MonitoredBusinessOverview.Reason.ProviderError(financialProviderError))
            } else {
                o.copy(missingFinancialReason = MonitoredBusinessOverview.Reason.NotIntegrated)
            }
            is MonitoredBusinessOverview.MissingData -> {
                val check1 = if (operationalProviderError != null) {
                    o.copy(missingOperationalReason = MonitoredBusinessOverview.Reason.ProviderError(operationalProviderError))
                } else {
                    o.copy(missingOperationalReason = MonitoredBusinessOverview.Reason.NotIntegrated)
                }
                val check2 = if (financialProviderError != null) {
                    check1.copy(missingFinancialReason = MonitoredBusinessOverview.Reason.ProviderError(financialProviderError))
                } else {
                    check1.copy(missingFinancialReason = MonitoredBusinessOverview.Reason.NotIntegrated)
                }
                check2
            }
        }
    }

    private suspend fun operationalOverview(
        business: MonitoredBusinessBasicInfo,
        params: LoadInfoParsedParams
    ): MonitoredBusinessOverview.OperationalOnly = when (business.operationalBoard) {
        DASHBOARD_OPERATIONAL.PICORTEX -> {
            val credentials = piCortexApiCredentialsDao.all(PiCortexApiCredentials::businessId isEqualTo business.uid).await().last()
            val dashboard = picortex.offeredTo(credentials).technicalDifferenceDashboardOf(params.start, params.end).await()
            val revenueVsExpensesChart = dashboard.charts.first { it.title.contentEquals("Revenue vs Expenses", ignoreCase = true) }
            val otherChart = (dashboard.charts - revenueVsExpensesChart).firstOrNull() ?: revenueVsExpensesChart
            val currency = dashboard.moneyCards.first().current.currency
            MonitoredBusinessOverview.OperationalOnly(
                business = business,
                cards = dashboard.moneyCards.filter {
                    it.details.containsAny(listOf("revenue", "expenses", "profit"))
                }.take(3).toInteroperableList(),
                revenueVsExpenses = revenueVsExpensesChart.map { Money.of(it, currency) },
                otherChart = otherChart.map { Money.Companion.of(it, currency) }
            )
        }
        else -> throw IllegalArgumentException("Unsupported operational dashboard $${business.operationalBoard}")
    }

    private suspend fun financialOverview(business: MonitoredBusinessBasicInfo, params: LoadInfoParsedParams): MonitoredBusinessOverview.FinancialOnly = when (business.financialBoard) {
        DASHBOARD_FINANCIAL.SAGE_ONE -> {
            val credentials = sageApiCredentialsDao.all(SageApiCredentials::businessId isEqualTo business.uid).await().last()
            val reportsService = sage.offeredTo(credentials.toCompany(business)).reports
            val prevIncomeStatement = reportsService.incomeStatement(params.prequel.toLocalDate(), params.start.toLocalDate())
            val currIncomeStatement = reportsService.incomeStatement(params.start.toLocalDate(), params.end.toLocalDate())
            val currBalanceSheet = reportsService.balanceSheet(params.end.toLocalDate())
            val pis = prevIncomeStatement.await().body
            val cis = currIncomeStatement.await().body
            val cbs = currBalanceSheet.await().body
            MonitoredBusinessOverview.FinancialOnly(
                business = business,
                cards = financialCards(pis, cis),
                assets = makeAssetsChart(cbs),
                balanceSheetChart = makeBalanceSheetChart(cbs)
            )
        }
        else -> throw IllegalArgumentException("Unsupported financial dashboard $${business.financialBoard}")
    }

    private fun financialCards(pis: IncomeStatement.Body, cis: IncomeStatement.Body): List<MoneyChangeBox> {
        val expenses = moneyChangeBoxOf(
            title = "Expenses",
            previous = pis.expenses.total,
            current = cis.expenses.total,
            increaseFeeling = ChangeFeeling.Bad,
            decreaseFeeling = ChangeFeeling.Good
        )

        val revenue = moneyChangeBoxOf(
            title = "Revenue",
            previous = pis.revenue.total,
            current = cis.revenue.total
        )

        val gp = moneyChangeBoxOf(
            title = "Gross Profit",
            previous = pis.grossProfit,
            current = cis.grossProfit
        )
        return listOf(expenses, revenue, gp)
    }

    private fun makeAssetsChart(bs: BalanceSheet.Body): Chart<Money> {
        val assets = bs.assets
        val allAssets = CategoryEntry(
            name = "Total Assets",
            currency = assets.current.currency,
            items = (assets.current.items + assets.fixed.items).sortedByDescending { it.value.amount }.toInteroperableList()
        )
        return makeChart(allAssets, "All accounted assets")
    }

    private fun makeChart(ce: CategoryEntry, details: String? = null): Chart<Money> = Chart(
        title = ce.name,
        description = details ?: ce.name,
        labels = ce.items.map { it.details }.toInteroperableList(),
        datasets = ce.items.map { Chart.DataSet(it.details, listOf(it.value)) }.toInteroperableList()
    )

    private fun makeBalanceSheetChart(bs: BalanceSheet.Body): BalanceSheetChart = BalanceSheetChart(
        assets = makeAssetsChart(bs),
        equityPlusLiabilities = makeChart(
            CategoryEntry(
                name = "Equity & Liabilities",
                currency = bs.equity.currency,
                items = (bs.equity.items + bs.liabilities.current.items + bs.liabilities.longTerm.items).toInteroperableList()
            )
        )
    )
}