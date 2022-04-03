package pimonitor.core.business.interventions.params

import pimonitor.core.business.utils.disbursements.CreateDisbursementRawParams
import validation.requiredNotBlank
import validation.requiredPositive
import kotlin.js.JsExport

@JsExport
interface CreateInterventionDisbursementRawParams : CreateDisbursementRawParams {
    val interventionId: String
}

fun CreateInterventionDisbursementRawParams.toValidatedParams() = CreateInterventionDisbursementParams(
    interventionId = requiredNotBlank(::interventionId),
    amount = requiredPositive(::amount),
    date = requiredPositive(::date),
)