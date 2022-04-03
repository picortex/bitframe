package pimonitor.client.business.utils.disbursements

data class DisbursementFormParams(
    override val amount: String,
    override val date: String
) : DisbursementRawFormParams