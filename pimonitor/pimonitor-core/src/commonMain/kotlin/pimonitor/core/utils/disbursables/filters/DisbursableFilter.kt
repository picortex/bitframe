package pimonitor.core.utils.disbursables.filters

import kotlinx.serialization.Serializable

@Serializable
data class DisbursableFilter(
    override val businessId: String?
) : DisbursableRawFilter