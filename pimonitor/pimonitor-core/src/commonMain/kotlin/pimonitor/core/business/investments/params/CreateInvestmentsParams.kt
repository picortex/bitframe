package pimonitor.core.business.investments.params

import kotlinx.serialization.Serializable

@Serializable
data class CreateInvestmentsParams(
    override val businessId: String,
    override val name: String,
    override val type: String,
    override val source: String,
    override val amount: Double,
    override val date: Double,
    override val details: String
) : CreateInvestmentsRawParams