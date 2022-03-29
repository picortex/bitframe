package pimonitor.core.business.utils.disbursements

data class CreateDisbursementParamsContextual(
    override val amount: Double,
    override val date: Double
) : CreateDisbursementRawParamsContextual