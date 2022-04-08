package pimonitor.core.business.utils.info

import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable

@Serializable
data class LoadInfoParams(
    override val businessId: String,
    override val start: String?,
    override val end: String?,
    override val timeZone: String = TimeZone.currentSystemDefault().id
) : LoadInfoRawParams
