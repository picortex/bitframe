package pimonitor.core.business.investments

import kotlinx.collections.interoperable.listOf
import kotlinx.serialization.Serializable

@Serializable
data class CaptureInvestmentsParams(
    override val businessId: String,
    override val name: String,
    override val type: String,
    override val source: String,
    override val amount: Double,
    override val date: Double,
    override val details: String
) : CaptureInvestmentsRawParams {
    fun toInvestment(created: InvestmentHistory.Created) = Investment(
        businessId = businessId,
        name = name,
        type = type,
        history = listOf(created),
        source = source,
        amount = amount,
        date = date,
        details = details
    )
}