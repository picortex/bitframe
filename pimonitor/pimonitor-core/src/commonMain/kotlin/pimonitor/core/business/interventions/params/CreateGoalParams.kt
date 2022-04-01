package pimonitor.core.business.interventions.params

import kotlinx.serialization.Serializable

@Serializable
data class CreateGoalParams(
    override val name: String,
    override val item: String,
    override val current: String,
    override val target: String,
    override val deadline: Double
) : CreateGoalRawParams