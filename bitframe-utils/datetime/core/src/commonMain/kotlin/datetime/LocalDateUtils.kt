package datetime

import kotlinx.datetime.LocalDate
import kotlinx.datetime.atTime


fun LocalDate.atEndOfDay() = atTime(23, 59, 59)

fun LocalDate.toSimpleDateTime() = atEndOfDay().toSimpleDateTime()