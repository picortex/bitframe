package pimonitor.client.business.utils.disbursements

import kotlin.js.JsExport

@JsExport
interface CreateDisbursementRawFormParams {
    val amount: String
    val date: String
}