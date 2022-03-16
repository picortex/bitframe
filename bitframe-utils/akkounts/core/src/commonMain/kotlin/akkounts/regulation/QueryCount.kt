package akkounts.regulation

import akkounts.provider.Vendor
import akkounts.provider.Owner
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmStatic

@Serializable
data class QueryCount(
    val requester: Owner,
    val vendor: Vendor,
    val initialRequested: LocalDateTime,
    val lastRequestedOn: LocalDateTime,
    val requestCount: Int
) {
    companion object {
        @JvmStatic
        fun initial(requester: Owner, vendor: Vendor): QueryCount {
            val now = Clock.System.now().toLocalDateTime(TimeZone.UTC)
            return QueryCount(
                requester = requester,
                vendor = vendor,
                initialRequested = now,
                lastRequestedOn = now,
                requestCount = 0
            )
        }
    }
}