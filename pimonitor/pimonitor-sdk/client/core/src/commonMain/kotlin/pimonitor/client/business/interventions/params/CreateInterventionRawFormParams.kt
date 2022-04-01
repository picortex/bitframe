package pimonitor.client.business.interventions.params

import datetime.SimpleDateTime
import pimonitor.core.business.interventions.params.CreateInterventionParams
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

fun CreateInterventionRawFormParams.toCreateInterventionParams(businessId: String) = CreateInterventionParams(
    businessId = businessId.takeIf { it.isNotBlank() } ?: throw BlankFieldException("businessId"),
    name = requiredNotBlank(::name),
    date = SimpleDateTime.parseDate(requiredNotBlank(::date)).timeStampInMillis,
    deadline = SimpleDateTime.parseDate(requiredNotBlank(::deadline)).timeStampInMillis,
    amount = requiredNotBlank(::amount).toDouble(),
    recommendations = requiredNotBlank(::recommendations),
)