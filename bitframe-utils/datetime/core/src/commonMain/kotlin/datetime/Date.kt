@file:Suppress("NON_EXPORTABLE_TYPE", "WRONG_EXPORTED_DECLARATION")

package datetime

import datetime.serializers.DateSerializer
import kotlinx.datetime.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@Serializable(with = DateSerializer::class)
interface Date : Comparable<Date> {
    companion object {
        fun parse(isoString: String): Date = DateImpl.parse(isoString)
        fun today(tz: TimeZone = TimeZone.UTC): Date = DateImpl(Clock.System.now().toLocalDateTime(tz).date)
        operator fun invoke(year: Int, monthNumber: Int, dayOfMonth: Int): Date = DateImpl(year, monthNumber, dayOfMonth)
        const val ISO_PATTER = "{YYYY}-{MM}-{DD}"
    }

    val year: Int
    val monthNumber: Int
    val month: Month
    val monthName: String
    val dayOfMonth: Int
    val dayOfWeek: DayOfWeek
    val dayOfYear: Int

    @JsName("minusPeriod")
    operator fun minus(period: DatePeriod): Date

    @JsName("minusDate")
    operator fun minus(other: Date): DatePeriod

    fun format(pattern: String): String

    fun toIsoFormat(): String
}