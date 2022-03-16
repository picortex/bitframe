package akkounts.reports.services

import akkounts.reports.balancesheet.BalanceSheet
import akkounts.reports.cashflow.CashFlow
import akkounts.reports.incomestatement.IncomeStatement
import kotlinx.datetime.LocalDate
import later.Later
import kotlin.js.JsExport

@JsExport
interface ReportsService {
    fun balanceSheet(at: LocalDate): Later<BalanceSheet>
    fun incomeStatement(start: LocalDate, end: LocalDate): Later<IncomeStatement>
    fun cashFlow(start: LocalDate, end: LocalDate): Later<CashFlow>
}