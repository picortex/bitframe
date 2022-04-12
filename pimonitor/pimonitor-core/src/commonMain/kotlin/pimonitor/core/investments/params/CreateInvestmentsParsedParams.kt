package pimonitor.core.investments.params

import datetime.Date
import kash.Currency
import kash.Money
import kotlinx.collections.interoperable.listOf
import kotlinx.serialization.Serializable
import pimonitor.core.investments.Investment
import pimonitor.core.investments.InvestmentHistory

@Serializable
data class CreateInvestmentsParsedParams(
    val businessId: String,
    val name: String,
    val type: String,
    val source: String,
    val amount: Money,
    val date: Date,
    val details: String
) {
    fun toInvestment(spaceId: String, created: InvestmentHistory.Created) = Investment(
        businessId = businessId,
        owningSpaceId = spaceId,
        name = name,
        type = type,
        history = listOf(created),
        disbursements = listOf(),
        source = source,
        amount = amount,
        date = date,
        details = details
    )
}