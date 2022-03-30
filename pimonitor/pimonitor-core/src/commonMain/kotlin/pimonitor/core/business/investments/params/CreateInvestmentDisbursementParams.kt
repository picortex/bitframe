package pimonitor.core.business.investments.params

import kotlinx.serialization.Serializable

@Serializable
data class CreateInvestmentDisbursementParams(
    override val investmentId: String,
    override val amount: Double,
    override val date: Double
) : CreateInvestmentDisbursementRawParams