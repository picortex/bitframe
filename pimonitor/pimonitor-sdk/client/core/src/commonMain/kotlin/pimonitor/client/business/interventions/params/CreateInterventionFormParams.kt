package pimonitor.client.business.interventions.params

data class CreateInterventionFormParams(
    override val name: String,
    override val interventionDate: String,
    override val interventionDeadline: String,
    override val amount: String,
    override val recommendations: String
) : CreateInterventionRawFormParams