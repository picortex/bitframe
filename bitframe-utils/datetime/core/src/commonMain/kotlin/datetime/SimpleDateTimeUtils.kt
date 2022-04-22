package datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun SimpleDateTime.toInstant() = Instant.fromEpochMilliseconds(epochMilliseconds = timeStampInMillis.toLong())

fun SimpleDateTime.toLocalDateTime(timezone: TimeZone = TimeZone.currentSystemDefault()) = toInstant().toLocalDateTime(timezone)
