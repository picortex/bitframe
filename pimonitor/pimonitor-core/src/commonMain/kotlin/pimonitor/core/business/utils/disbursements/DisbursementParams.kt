package pimonitor.core.business.utils.disbursements

import kotlinx.serialization.Serializable

@Serializable
data class DisbursementParams(
    override val amount: String,
    override val date: String
) : DisbursementRawParams