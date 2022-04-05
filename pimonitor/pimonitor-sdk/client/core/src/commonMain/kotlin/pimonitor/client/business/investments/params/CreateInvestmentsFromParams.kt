package pimonitor.client.business.investments.params

data class CreateInvestmentsFromParams(
    override val name: String,
    override val type: String,
    override val source: String,
    override val amount: String,
    override val date: String,
    override val details: String
) : CreateInvestmentsRawFormParams