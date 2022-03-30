package pimonitor.core.business.utils.info

import kotlinx.serialization.Serializable

@Serializable // TODO: This is not serialized well
data class LoadInfoParams(
    override val businessId: String,
    override val start: Double,
    override val end: Double
) : LoadInfoRawParams
