@file:JvmName("LocalDateUtils")

package akkounts.utils

import kotlinx.datetime.*
import kotlin.jvm.JvmName


private fun Int.to2Digits() = if (this < 10) "0$this" else "$this"

fun LocalDateTime.toYYYYMMDD(separator: Char = '-') = date.toYYYYMMDD(separator)

fun LocalDate.toYYYYMMDD(separator: Char = '-'): String {
    return "$year$separator${monthNumber.to2Digits()}$separator${dayOfMonth.to2Digits()}"
}

fun today(): LocalDate = Clock.System.now().toLocalDateTime(TimeZone.UTC).date