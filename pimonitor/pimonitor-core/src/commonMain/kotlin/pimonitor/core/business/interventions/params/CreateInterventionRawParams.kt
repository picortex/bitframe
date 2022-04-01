package pimonitor.core.business.interventions.params

import validation.requiredNotBlank
import validation.requiredPositive
import kotlin.js.JsExport

@JsExport
interface CreateInterventionRawParams {
    val name: String
    val date: Double
    val deadline: Double
    val amount: Double
    val recommendations: String
}

fun CreateInterventionRawParams.toValidatedParams() = CreateInterventionParams(
    name = requiredNotBlank(::name),
    date = requiredPositive(::date),
    deadline = requiredPositive(::deadline),
    amount = requiredPositive(::amount),
    recommendations = requiredNotBlank(::recommendations),
)