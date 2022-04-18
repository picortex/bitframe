package pimonitor.core.interventions.params

import kotlinx.serialization.Serializable

@Serializable
data class CreateInterventionDisbursementParams(
    override val interventionId: String,
    override val amount: String,
    override val date: String
) : CreateInterventionDisbursementRawParams