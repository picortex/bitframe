@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package akkounts.reports.utils

import akkounts.provider.Owner
import akkounts.provider.Vendor
import datetime.Date
import kash.Currency
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
sealed interface FinancialReportHeader {
    val vendor: Vendor
    val currency: Currency
    val owner: Owner

    @Serializable
    data class Durational(
        override val vendor: Vendor,
        override val currency: Currency,
        override val owner: Owner,
        val start: Date,
        val end: Date
    ) : FinancialReportHeader

    @Serializable
    data class Snapshot(
        override val vendor: Vendor,
        override val owner: Owner,
        override val currency: Currency,
        val endOf: Date
    ) : FinancialReportHeader
}