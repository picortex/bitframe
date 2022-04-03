package datetime

import kotlinx.datetime.*

fun LocalDateTime.toSimpleDateTime(timezone: TimeZone = TimeZone.UTC) = SimpleDateTime(
    timeStampInMillis = toInstant(timezone).toEpochMilliseconds().toDouble()
)