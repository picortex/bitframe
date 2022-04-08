package pimonitor.core.business.utils.info

import datetime.Date
import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable

@Serializable
data class LoadInfoParams(
    override val businessId: String,
    override val start: String?,
    override val end: String?,
    override val timeZone: String = TimeZone.currentSystemDefault().id
) : LoadInfoRawParams {
    constructor(
        businessId: String,
        start: Date?,
        end: Date?,
        timeZone: String = TimeZone.currentSystemDefault().id
    ) : this(businessId, start?.toIsoFormat(), end?.toString(), timeZone)
}
