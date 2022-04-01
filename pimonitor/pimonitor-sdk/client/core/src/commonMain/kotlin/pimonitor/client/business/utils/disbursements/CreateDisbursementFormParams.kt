package pimonitor.client.business.utils.disbursements

data class CreateDisbursementFormParams(
    override val amount: String,
    override val date: String
) : CreateDisbursementRawFormParams