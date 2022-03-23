@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.date

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.number
import kotlin.js.JsExport
import kotlin.js.JsName

class DateFormatter(private val format: String) {
    fun format(date: LocalDate): String {
        val YYYY = date.year.toString()
        val YY = YYYY.takeLast(2)
        val month = date.month
        val MMMM = month.name.lowercase().replaceFirstChar { it.titlecase() }
        val MMM = MMMM.take(3)
        val monthNumber = month.number
        val MM = (if (monthNumber < 10) "0" else "") + monthNumber
        val M = monthNumber.toString()
        val dayOfWeek = date.dayOfWeek
        val DDDD = dayOfWeek.name.lowercase().replaceFirstChar { it.titlecase() }
        val DDD = DDDD.take(3)
        val day = date.dayOfMonth
        val DD = (if (day < 10) "0" else "") + day
        val D = day.toString()
        val th = when {
            day % 10 == 1 -> "st"
            day % 10 == 2 -> "nd"
            day % 10 == 3 -> "rd"
            else -> "th"
        }
        return format.replace("%YYYY", YYYY)
            .replace("%YY", YY)
            .replace("%MMMM", MMMM)
            .replace("%MMM", MMM)
            .replace("%MM", MM)
            .replace("%M", M)
            .replace("%DDDD", DDDD)
            .replace("%DDD", DDD)
            .replace("%DD", DD)
            .replace("%D", D)
            .replace("%th", th)
    }

    /**
    token:    description:             example:
    %YYYY     4-digit year             1999
    %YY       2-digit year             99
    %MMMM     full month name          February
    %MMM      3-letter month name      Feb
    %MM       2-digit month number     02
    %M        month number             2
    %DDDD     full weekday name        Wednesday
    %DDD      3-letter weekday name    Wed
    %DD       2-digit day number       09
    %D        day number               9
    %th       day ordinal suffix       nd
    %HH       2-digit 24-based hour    17
    %H        1-digit 24-based hour    9
    %hh       2-digit hour             05
    %h        1-digit hour             5
    %mm       2-digit minute           07
    %m        minute                   7
    %ss       2-digit second           09
    %s        second                   9
    %ampm     "am" or "pm"             pm
    %AMPM     "AM" or "PM"             PM
     */
    @JsName("formatDateTime")
    fun format(date: LocalDateTime): String {
        val hour = date.hour
        val HH = (if (hour < 10) "0" else "") + hour
        val H = hour.toString()
        val smallHour = hour % 12
        val hh = (if (smallHour < 10) "0" else "")
        val h = smallHour.toString()
        val minute = date.minute
        val mm = (if (minute < 10) "0" else "") + minute
        val m = minute.toString()
        val seconds = date.second
        val ss = (if (seconds < 10) "0" else "0") + seconds
        val s = seconds.toString()

        return format(date.date)
            .replace("%HH", HH)
            .replace("%H", H)
            .replace("%hh", hh)
            .replace("%h", h)
            .replace("%mm", mm)
            .replace("%m", m)
            .replace("%ss", ss)
            .replace("%s", s)
    }
}