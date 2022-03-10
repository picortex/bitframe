@file:JsExport

package akkounts.reports.balancesheet

import akkounts.reports.utils.CategoryEntry
import akkounts.provider.Vendor
import akkounts.provider.Owner
import kash.Currency
import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class BalanceSheet(
    /** BalanceSheet Id */
    val uid: String,
    val header: Header,
    val body: Data,
) {

    @Serializable
    data class Header(
        val vendor: Vendor,
        val owner: Owner,
        val currency: Currency,
        val endOf: LocalDate
    )

    @Serializable
    data class Data(
        val assets: Assets,
        val equity: CategoryEntry,
        val liabilities: Liabilities,
    ) {
        val version = "3.0"

        val verdict by lazy {
            Verdict(
                assets = assets.total,
                equityPlusLiabilities = equity.total + liabilities.total
            )
        }

        @Serializable
        data class Assets(
            val current: CategoryEntry,
            val fixed: CategoryEntry
        ) {
            val total by lazy { current.total + fixed.total }
        }

        @Serializable
        data class Liabilities(
            val current: CategoryEntry,
            @SerialName("long-term") val longTerm: CategoryEntry,
        ) {
            val total by lazy { current.total + longTerm.total }
        }

        @Serializable
        data class Verdict(
            @SerialName("assets") val assets: Int,
            @SerialName("equity + liabilities") val equityPlusLiabilities: Int
        )
    }
}