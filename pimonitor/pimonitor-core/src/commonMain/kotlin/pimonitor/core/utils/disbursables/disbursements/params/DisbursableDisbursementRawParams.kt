package pimonitor.core.utils.disbursables.disbursements.params

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface DisbursableDisbursementRawParams : DisbursementRawParams {
    val disbursableId: String
}

fun DisbursableDisbursementRawParams.toValidatedParams() = DisbursableDisbursementParams(
    disbursableId = requiredNotBlank(::disbursableId),
    amount = requiredNotBlank(::amount),
    date = requiredNotBlank(::date),
)