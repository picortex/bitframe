package pimonitor.core.business.investments.params

data class CreateInvestmentsParamsContextual(
    override val name: String,
    override val type: String,
    override val source: String,
    override val amount: Double,
    override val date: Double,
    override val details: String
) : CreateInvestmentsRawParamsContextual