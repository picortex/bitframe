package pimonitor.core.business.interventions.params

import datetime.Date
import kash.Currency
import kash.Money
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface InterventionRawParams {
    val businessId: String
    val name: String
    val date: String
    val deadline: String
    val amount: String
    val recommendations: String
}

fun InterventionRawParams.toValidatedParams() = InterventionParams(
    businessId = requiredNotBlank(::businessId),
    name = requiredNotBlank(::name),
    date = requiredNotBlank(::date),
    deadline = requiredNotBlank(::deadline),
    amount = requiredNotBlank(::amount),
    recommendations = requiredNotBlank(::recommendations),
)

fun InterventionParams.toParsedParams(currency: Currency) = InterventionParsedParams(
    businessId = businessId,
    name = name,
    date = Date.parse(date),
    deadline = Date.parse(deadline),
    amount = Money.of(amount.toDouble(), currency),
    recommendations = recommendations,
)