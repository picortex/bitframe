package pimonitor.core.utils.disbursables.disbursements.params

import kotlinx.serialization.Serializable

@Serializable
data class DisbursableDisbursementParams(
    override val disbursableId: String,
    override val amount: String,
    override val date: String
) : DisbursableDisbursementRawParams