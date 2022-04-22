package datetime

import kotlinx.datetime.LocalDate

fun Date.toLocalDate() = LocalDate(year, monthNumber, dayOfMonth)