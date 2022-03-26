@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package akkounts.reports.incomestatement

import akkounts.reports.utils.CategoryEntry
import akkounts.provider.Vendor
import akkounts.provider.Owner
import akkounts.reports.FinancialReport
import akkounts.reports.FinancialReportHeader
import kash.Currency
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class IncomeStatement(
    /** Income statement data */
    override val uid: String,
    override val header: Header,
    override val body: Body
) : FinancialReport {

    @Serializable
    data class Header(
        override val vendor: Vendor,
        override val currency: Currency,
        override val owner: Owner,
        val start: LocalDate,
        val end: LocalDate
    ) : FinancialReportHeader

    @Serializable
    data class Body(
        val income: CategoryEntry,
        val otherIncome: CategoryEntry,
        val costOfSales: CategoryEntry,
        val expenses: CategoryEntry,
        val otherExpenses: CategoryEntry,
        val taxes: CategoryEntry
    ) {
        val grossProfit by lazy { income.total + otherIncome.total - costOfSales.total }
        val netIncomeBeforeTaxes by lazy { grossProfit - (expenses.total + otherExpenses.total) }
        val netIncomeAfterTaxes by lazy { netIncomeBeforeTaxes - taxes.total }
        val version: String = "2.0"
    }
}