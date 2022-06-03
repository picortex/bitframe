package pimonitor.core.investments.filters

import kotlinx.serialization.Serializable

@Serializable
data class InvestmentFilter(
    override val businessId: String?
) : InvestmentRawFilter