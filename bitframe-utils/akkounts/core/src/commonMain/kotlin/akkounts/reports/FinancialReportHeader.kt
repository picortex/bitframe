@file:Suppress("NON_EXPORTABLE_TYPE")

package akkounts.reports

import akkounts.provider.Owner
import akkounts.provider.Vendor
import kash.Currency
import kotlin.js.JsExport

@JsExport
interface FinancialReportHeader {
    val vendor: Vendor
    val currency: Currency
    val owner: Owner
}