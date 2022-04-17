package pimonitor.core.business.interventions.params

import pimonitor.core.utils.disbursables.disbursements.params.DisbursementRawParams
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface CreateInterventionDisbursementRawParams : DisbursementRawParams {
    val interventionId: String
}

fun CreateInterventionDisbursementRawParams.toValidatedParams() = CreateInterventionDisbursementParams(
    interventionId = requiredNotBlank(::interventionId),
    amount = requiredNotBlank(::amount),
    date = requiredNotBlank(::date),
)