package pimonitor.core.utils.disbursements.params

import kotlinx.serialization.Serializable

@Serializable
data class DisbursementParams(
    override val amount: String,
    override val date: String
) : DisbursementRawParams