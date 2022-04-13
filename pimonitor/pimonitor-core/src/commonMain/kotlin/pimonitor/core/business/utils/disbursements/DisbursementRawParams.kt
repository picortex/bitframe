package pimonitor.core.business.utils.disbursements

import datetime.Date
import kash.Currency
import kash.Money
import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface DisbursementRawParams {
    val amount: String
    val date: String
}

fun DisbursementRawParams.toValidatedParams() = DisbursementParams(
    amount = requiredNotBlank(::amount),
    date = requiredNotBlank(::date),
)

fun DisbursementRawParams.toParsedParams(currency: Currency): DisbursementParsedParams {
    val validated = toValidatedParams()
    return DisbursementParsedParams(
        amount = Money.of(validated.amount.toDouble(), currency),
        date = Date.parse(validated.date)
    )
}