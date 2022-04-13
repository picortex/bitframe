package pimonitor.core.investments.params

import bitframe.core.Identified
import datetime.Date
import kash.Currency
import kash.Money
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface InvestmentsRawParams {
    val businessId: String
    val name: String
    val type: String
    val source: String
    val amount: String
    val date: String
    val details: String
}

fun InvestmentsRawParams.toValidatedParams() = InvestmentsParams(
    businessId = requiredNotBlank(::businessId),
    name = requiredNotBlank(::name),
    type = requiredNotBlank(::type),
    source = requiredNotBlank(::source),
    amount = requiredNotBlank(::amount),
    date = requiredNotBlank(::date),
    details = requiredNotBlank(::details),
)

fun InvestmentsRawParams.toIdentifiedParams(investmentId: String) = Identified(
    uid = investmentId,
    body = toValidatedParams()
)

fun InvestmentsRawParams.toParsedParams(currency: Currency): InvestmentsParsedParams {
    val validated = toValidatedParams()
    return InvestmentsParsedParams(
        businessId = validated.businessId,
        name = validated.name,
        type = validated.type,
        source = validated.source,
        amount = Money.of(validated.amount.toDouble(), currency),
        date = Date.parse(validated.date),
        details = validated.details,
    )
}