package pimonitor.core.business.utils.disbursements

import kotlin.js.JsExport

@JsExport
interface CreateDisbursementRawParamsContextual {
    val amount: Double
    val date: Double
}