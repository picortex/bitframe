package datetime

import kotlinx.datetime.*

fun LocalDateTime.toSimpleDateTime() = SimpleDateTime(
    timeStampInMillis = toInstant(TimeZone.UTC).toEpochMilliseconds().toDouble()
)