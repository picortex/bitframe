package pimonitor.core.investments

import kotlinx.serialization.Serializable

@Serializable
data class InvestmentFilter(
    val businessId: String? = null
)