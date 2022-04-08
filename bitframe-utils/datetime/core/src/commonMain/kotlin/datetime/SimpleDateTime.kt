@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package datetime

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
data class SimpleDateTime(
    val timeStampInMillis: Double
) : Comparable<SimpleDateTime> {

    companion object {
        val now get() = SimpleDateTime(Clock.System.now().toEpochMilliseconds().toDouble())
        fun parseDate(isoString: String): SimpleDateTime = LocalDate.parse(isoString).toSimpleDateTime()
    }

    override fun compareTo(other: SimpleDateTime) = timeStampInMillis.compareTo(other.timeStampInMillis)

    fun format(pattern: String) = DateFormatter(pattern).format(timeStampInMillis)

    @JsName("formatWithTimeZone")
    fun format(template: String, timeZone: TimeZone) = DateFormatter(template).format(timeStampInMillis, timeZone)
}
