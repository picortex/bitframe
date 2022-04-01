package pimonitor.client.business.utils.disbursements

import kotlin.js.JsExport

@JsExport
interface DisbursementRawFormParams {
    val amount: String
    val date: String
}