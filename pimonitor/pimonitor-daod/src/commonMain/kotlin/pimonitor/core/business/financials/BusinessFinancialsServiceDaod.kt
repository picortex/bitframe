package pimonitor.core.business.financials

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.sage.SageOneZAService
import akkounts.sage.SageOneZAUserCompany
import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.get
import bitframe.core.isEqualTo
import kotlinx.datetime.*
import later.await
import later.later
import pimonitor.core.accounting.FINANCIAL_REPORTS
import pimonitor.core.businesses.DASHBOARD_FINANCIAL
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.businesses.results.AvailableReportsResults
import pimonitor.core.configs.sageService
import pimonitor.core.invites.InfoResults
import pimonitor.core.sage.SageApiCredentials
import pimonitor.core.sage.toCompany

class BusinessFinancialsServiceDaod(
    val config: ServiceConfigDaod
) : BusinessFinancialsServiceCore {

    private val factory get() = config.daoFactory
    private val monitoredBusinessesDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val sageCredentialsDao by lazy { factory.get<SageApiCredentials>() }
    private val sage by lazy { config.sageService }

    private suspend fun load(rb: RequestBody.Authorized<String>) = monitoredBusinessesDao.load(uid = rb.data).await()

    override fun balanceSheet(rb: RequestBody.Authorized<String>) = config.scope.later {
        val business = load(rb)
        when (business.financialBoard) {
            DASHBOARD_FINANCIAL.NONE -> {
                InfoResults.NotShared(
                    business = business,
                    message = "${business.name} has not shared their reports with any accounting system"
                ) as InfoResults<BalanceSheet>
            }
            DASHBOARD_FINANCIAL.SAGE_ONE -> {
                val cred = sageCredentialsDao.all(condition = SageApiCredentials::businessId isEqualTo business.uid).await().first()
                val company = cred.toCompany(business)
                val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
                InfoResults.Shared(
                    business = business,
                    data = sage.offeredTo(company).reports.balanceSheet(at = today).await()
                )
            }
            else -> error("Business is connected to an unknown accounting provider")
        }
    }

    override fun availableReports(rb: RequestBody.Authorized<String>) = config.scope.later {
        val business = load(rb)
        val reports = when (business.financialBoard) {
            DASHBOARD_FINANCIAL.NONE -> kotlinx.collections.interoperable.emptyList()
            DASHBOARD_FINANCIAL.SAGE_ONE -> kotlinx.collections.interoperable.listOf(
                FINANCIAL_REPORTS.BALANCE_SHEET,
                FINANCIAL_REPORTS.INCOME_STATEMENT
            )
            else -> error("Business is connected to an unknown operational board provider")
        }
        AvailableReportsResults(business, reports)
    }

    override fun incomeStatement(rb: RequestBody.Authorized<String>) = config.scope.later {
        val business = load(rb)

        when (business.financialBoard) {
            DASHBOARD_FINANCIAL.NONE -> {
                InfoResults.NotShared(
                    business = business,
                    message = "${business.name} has not shared their reports with any accounting system"
                ) as InfoResults<IncomeStatement>
            }
            DASHBOARD_FINANCIAL.SAGE_ONE -> {
                val cred = sageCredentialsDao.all(
                    condition = SageApiCredentials::businessId isEqualTo business.uid
                ).await().last()
                val company = cred.toCompany(business)
                val today = Clock.System.todayAt(TimeZone.currentSystemDefault())
                val lastMonth = today - DatePeriod(months = 1)
                InfoResults.Shared(
                    business = business,
                    data = sage.offeredTo(company).reports.incomeStatement(start = lastMonth, end = today).await()
                )
            }
            else -> error("Business is connected to an unknown accounting provider")
        }
    }
}