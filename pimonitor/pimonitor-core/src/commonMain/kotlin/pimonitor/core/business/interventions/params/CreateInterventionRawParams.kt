package pimonitor.core.business.interventions.params

import datetime.SimpleDateTime
import kash.Currency
import kotlinx.collections.interoperable.listOf
import pimonitor.core.business.interventions.Intervention
import pimonitor.core.business.interventions.InterventionHistory
import validation.requiredNotBlank
import validation.requiredPositive
import kotlin.js.JsExport

@JsExport
interface CreateInterventionRawParams {
    val businessId: String
    val name: String
    val date: Double
    val deadline: Double
    val amount: Double
    val recommendations: String
}

fun CreateInterventionRawParams.toValidatedParams() = CreateInterventionParams(
    businessId = requiredNotBlank(::businessId),
    name = requiredNotBlank(::name),
    date = requiredPositive(::date),
    deadline = requiredPositive(::deadline),
    amount = requiredPositive(::amount),
    recommendations = requiredNotBlank(::recommendations),
)

fun CreateInterventionRawParams.toIntervention(created: InterventionHistory.Created) = Intervention(
    businessId = businessId,
    name = name,
    history = listOf(created),
    disbursements = listOf(),
    amount = Currency.ZAR.of(amount),
    date = SimpleDateTime(date),
    deadline = SimpleDateTime(deadline),
    goals = listOf(),
    recommendations = recommendations
)