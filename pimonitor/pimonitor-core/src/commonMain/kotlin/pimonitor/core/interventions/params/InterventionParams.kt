package pimonitor.core.interventions.params

import kotlinx.serialization.Serializable

@Serializable
data class InterventionParams(
    override val businessId: String,
    override val name: String,
    override val date: String,
    override val deadline: String,
    override val amount: String,
    override val recommendations: String,
) : InterventionRawParams