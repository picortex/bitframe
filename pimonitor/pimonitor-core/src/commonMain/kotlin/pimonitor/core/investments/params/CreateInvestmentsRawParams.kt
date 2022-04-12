package pimonitor.core.investments.params

import datetime.Date
import kash.Currency
import kash.Money
import validation.BlankFieldException
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface CreateInvestmentsRawParams {
    val businessId: String
    val name: String
    val type: String
    val source: String
    val amount: String
    val date: String
    val details: String
}

fun CreateInvestmentsRawParams.toValidatedParams() = CreateInvestmentsParams(
    businessId = businessId.takeIf { it.isNotBlank() } ?: throw BlankFieldException("businessId"),
    name = requiredNotBlank(::name),
    type = requiredNotBlank(::type),
    source = requiredNotBlank(::source),
    amount = requiredNotBlank(::amount),
    date = requiredNotBlank(::date),
    details = requiredNotBlank(::details),
)

fun CreateInvestmentsRawParams.toParsedParams(currency: Currency): CreateInvestmentsParsedParams {
    val validated = toValidatedParams()
    return CreateInvestmentsParsedParams(
        businessId = validated.businessId,
        name = validated.name,
        type = validated.type,
        source = validated.source,
        amount = Money.of(validated.amount.toDouble(), currency),
        date = Date.parse(validated.date),
        details = validated.details,
    )
}