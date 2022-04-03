package datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atTime


fun LocalDate.atEndOfDay() = atTime(23, 59, 59)

fun LocalDate.toSimpleDateTime(timeZone: TimeZone = TimeZone.UTC) = atEndOfDay().toSimpleDateTime(timeZone)