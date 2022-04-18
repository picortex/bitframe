package pimonitor.client.interventions.params

import pimonitor.core.interventions.params.InterventionParams
import validation.BlankFieldException
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface CreateInterventionRawFormParams {
    val name: String
    val date: String
    val deadline: String
    val amount: String
    val recommendations: String
}

fun CreateInterventionRawFormParams.toCreateInterventionParams(businessId: String) = InterventionParams(
    businessId = businessId.takeIf { it.isNotBlank() } ?: throw BlankFieldException("businessId"),
    name = requiredNotBlank(::name),
    date = requiredNotBlank(::date),
    deadline = requiredNotBlank(::deadline),
    amount = requiredNotBlank(::amount),
    recommendations = requiredNotBlank(::recommendations),
)