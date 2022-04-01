package pimonitor.client.business.interventions.params

data class CreateInterventionFormParams(
    override val name: String,
    override val date: String,
    override val deadline: String,
    override val amount: String,
    override val recommendations: String
) : CreateInterventionRawFormParams