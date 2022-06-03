package pimonitor.core.utils.disbursables.params

import kotlin.js.JsExport

@JsExport
interface DisbursableRawParams {
    val name: String
    val amount: String
}