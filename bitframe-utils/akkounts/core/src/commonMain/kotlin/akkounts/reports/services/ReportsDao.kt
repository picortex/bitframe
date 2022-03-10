package akkounts.reports.services

import akkounts.provider.Owner
import akkounts.provider.Vendor
import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.cashflow.CashFlow
import akkounts.reports.incomestatement.IncomeStatement
import kotlinx.datetime.LocalDate
import later.Later

interface ReportsDao {
    fun save(
        owner: Owner,
        vendor: Vendor,
        header: BalanceSheet.Header,
        body: BalanceSheet.Data
    ): Later<BalanceSheet>

    fun loadBalanceSheet(
        owner: Owner,
        vendor: Vendor,
        at: LocalDate
    ): Later<BalanceSheet?>

    fun save(
        owner: Owner,
        vendor: Vendor,
        header: IncomeStatement.Header,
        body: IncomeStatement.Data
    ): Later<IncomeStatement>

    fun loadIncomeStatement(
        ownerId: String,
        start: LocalDate,
        end: LocalDate
    ): Later<IncomeStatement?>

    fun save(
        owner: Owner,
        vendor: Vendor,
        header: CashFlow.Header,
        body: CashFlow.Data
    ): Later<CashFlow>

    fun loadCashFlow(
        owner: Owner,
        vendor: Vendor,
        start: LocalDate,
        end: LocalDate
    ): Later<CashFlow?>
}