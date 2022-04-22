package pimonitor.core.investments.params

import datetime.Date
import kash.Currency
import kash.Money
import kotlinx.serialization.Serializable

@Serializable
open class InvestmentParams(
    override val businessId: String,
    override val name: String,
    override val type: String,
    override val source: String,
    override val amount: String,
    override val date: String,
    override val details: String
) : InvestmentRawParams {
    open fun toParsedParams(currency: Currency) = InvestmentsParsedParams(
        businessId = businessId,
        name = name,
        type = type,
        source = source,
        amount = Money.of(amount.toDouble(), currency),
        date = Date.parse(date),
        details = details,
    )
}