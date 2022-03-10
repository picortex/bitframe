@file:JsExport

package akkounts.reports.incomestatement

import akkounts.reports.utils.CategoryEntry
import akkounts.provider.Vendor
import akkounts.provider.Owner
import kash.Currency
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class IncomeStatement(
    /** Income statement data */
    val uid: String,
    val header: Header,
    val body: Data
) {

    @Serializable
    data class Header(
        val vendor: Vendor,
        val currency: Currency,
        val owner: Owner,
        val start: LocalDate,
        val end: LocalDate
    )

    @Serializable
    data class Data(
        val income: CategoryEntry,
        val otherIncome: CategoryEntry,
        val costOfSales: CategoryEntry,
        val expenses: CategoryEntry,
        val otherExpenses: CategoryEntry,
        val taxes: CategoryEntry
    ) {
        val grossProfit by lazy { income.total + otherIncome.total - costOfSales.total }
        val version: String = "2.0"
    }
}