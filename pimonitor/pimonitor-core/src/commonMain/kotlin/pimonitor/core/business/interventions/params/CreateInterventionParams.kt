package pimonitor.core.business.interventions.params

import kotlinx.serialization.Serializable

@Serializable
data class CreateInterventionParams(
    override val businessId: String,
    override val name: String,
    override val date: Double,
    override val deadline: Double,
    override val amount: Double,
    override val recommendations: String,
) : CreateInterventionRawParams