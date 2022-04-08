package akkounts.reports

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.cashflow.CashFlow
import akkounts.reports.incomestatement.IncomeStatement
import akkounts.reports.utils.FinancialReportHeader
import kotlin.js.JsExport

@JsExport
interface FinancialReport {
    val uid: String
    val header: FinancialReportHeader
    val body: Any

    val isBalanceSheet get() = this is BalanceSheet

    val asBalanceSheet get() = this as BalanceSheet

    val isIncomeStatement get() = this is IncomeStatement

    val asIncomeStatement get() = this as IncomeStatement

    val isCashFlow get() = this is CashFlow

    val asCashFlow get() = this as CashFlow
}