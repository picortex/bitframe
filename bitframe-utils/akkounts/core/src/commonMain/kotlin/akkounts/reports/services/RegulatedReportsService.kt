package akkounts.reports.services

import akkounts.provider.Owner
import akkounts.provider.Vendor
import akkounts.regulation.QueryRegulator
import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.cashflow.CashFlow
import akkounts.reports.incomestatement.IncomeStatement
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.datetime.LocalDate
import later.Later
import later.await
import later.later

class RegulatedReportsService(
    private val regulator: QueryRegulator,
    private val service: ReportsService,
    private val vendor: Vendor,
    private val owner: Owner,
    private val dao: ReportsDao,
    private val scope: CoroutineScope = CoroutineScope(SupervisorJob())
) : ReportsService {

    override fun balanceSheet(at: LocalDate): Later<BalanceSheet> = scope.later {
        val status = regulator.checkRestriction(owner, vendor).await()
        when (status) {
            QueryRegulator.Status.Allowed -> {
                service.balanceSheet(at).await()
            }
            QueryRegulator.Status.Blocked -> TODO()
        }
    }

    override fun incomeStatement(start: LocalDate, end: LocalDate): Later<IncomeStatement> {
        TODO("Not yet implemented")
    }

    override fun cashFlow(start: LocalDate, end: LocalDate): Later<CashFlow> {
        TODO("Not yet implemented")
    }
}