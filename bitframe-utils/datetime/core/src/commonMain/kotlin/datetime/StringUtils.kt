package datetime

import kotlinx.datetime.TimeZone

fun String.toDate() = Date.parse(this)

fun String.toTimeZone() = TimeZone.of(this)