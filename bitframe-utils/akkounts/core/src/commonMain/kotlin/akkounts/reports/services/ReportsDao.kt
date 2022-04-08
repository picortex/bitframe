package akkounts.reports.services

import akkounts.provider.Owner
import akkounts.provider.Vendor
import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.cashflow.CashFlow
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.reports.utils.FinancialReportHeader
import kotlinx.datetime.LocalDate
import later.Later

interface ReportsDao {
    fun save(
        owner: Owner,
        vendor: Vendor,
        header: FinancialReportHeader.Snapshot,
        body: BalanceSheet.Body
    ): Later<BalanceSheet>

    fun loadBalanceSheet(
        owner: Owner,
        vendor: Vendor,
        at: LocalDate
    ): Later<BalanceSheet?>

    fun save(
        owner: Owner,
        vendor: Vendor,
        header: FinancialReportHeader.Durational,
        body: IncomeStatement.Body
    ): Later<IncomeStatement>

    fun loadIncomeStatement(
        ownerId: String,
        start: LocalDate,
        end: LocalDate
    ): Later<IncomeStatement?>

    fun save(
        owner: Owner,
        vendor: Vendor,
        header: FinancialReportHeader.Snapshot,
        body: CashFlow.Body
    ): Later<CashFlow>

    fun loadCashFlow(
        owner: Owner,
        vendor: Vendor,
        start: LocalDate,
        end: LocalDate
    ): Later<CashFlow?>
}