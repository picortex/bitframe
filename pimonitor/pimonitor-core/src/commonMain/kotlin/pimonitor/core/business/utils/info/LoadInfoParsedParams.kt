package pimonitor.core.business.utils.info

import datetime.Date
import kotlinx.serialization.Serializable

@Serializable
class LoadInfoParsedParams(
    val businessId: String,
    val start: Date,
    val end: Date
) {
    val prequel by lazy {
        val duration = end - start
        start - duration
    }
}