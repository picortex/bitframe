@file:JsExport

package akkounts.reports.cashflow

import akkounts.reports.utils.CategoryEntry
import akkounts.provider.Vendor
import akkounts.provider.Owner
import kash.Currency
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class CashFlow(
    val uid: String,
    val header: Header,
    val body: Data
) {
    @Serializable
    data class Header(
        val vendor: Vendor,
        val owner: Owner,
        val currency: Currency,
        val start: LocalDate,
        val end: LocalDate
    )

    @Serializable
    data class Data(
        val startAmount: Int,
        val operating: CategoryEntry,
        val investing: CategoryEntry,
        val financing: CategoryEntry
    ) {
        val endAmount by lazy { startAmount + operating.total + investing.total + financing.total }
    }
}