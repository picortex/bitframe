package pimonitor.core.business.utils.info

import datetime.Date
import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmOverloads

@Serializable
data class LoadInfoParams(
    override val businessId: String,
    override val start: String? = null,
    override val end: String? = null,
    override val timeZone: String = TimeZone.currentSystemDefault().id
) : LoadInfoRawParams {
    @JvmOverloads
    constructor(
        businessId: String,
        start: Date,
        end: Date,
        timeZone: String = TimeZone.currentSystemDefault().id
    ) : this(businessId, start?.toIsoFormat(), end?.toString(), timeZone)
}
