package pimonitor.core.utils.disbursables.filters

import kotlin.js.JsExport

@JsExport
interface DisbursableRawFilter {
    val businessId: String?
}

fun DisbursableRawFilter?.toValidateParams() = DisbursableFilter(
    businessId = this?.businessId
)