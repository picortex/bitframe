@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package datetime

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class SimpleDateTime(
    val timeStampInMillis: Double
) : Comparable<SimpleDateTime> {

    companion object {
        val now get() = SimpleDateTime(Clock.System.now().toEpochMilliseconds().toDouble())
    }

    override fun compareTo(other: SimpleDateTime) = timeStampInMillis.compareTo(other.timeStampInMillis)
    fun format(template: String) = DateFormatter(template).format(timeStampInMillis)
    fun formatWithTimeZone(template: String, timeZone: TimeZone) = DateFormatter(template).format(timeStampInMillis, timeZone)
}
