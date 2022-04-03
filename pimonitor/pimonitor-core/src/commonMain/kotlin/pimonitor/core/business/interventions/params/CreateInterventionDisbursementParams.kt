package pimonitor.core.business.interventions.params

import kotlinx.serialization.Serializable

@Serializable
data class CreateInterventionDisbursementParams(
    override val interventionId: String,
    override val amount: Double,
    override val date: Double
) : CreateInterventionDisbursementRawParams