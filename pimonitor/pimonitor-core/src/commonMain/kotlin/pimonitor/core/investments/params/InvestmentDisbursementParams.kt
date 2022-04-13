package pimonitor.core.investments.params

import kotlinx.serialization.Serializable

@Serializable
data class InvestmentDisbursementParams(
    override val investmentId: String,
    override val amount: String,
    override val date: String
) : InvestmentDisbursementRawParams