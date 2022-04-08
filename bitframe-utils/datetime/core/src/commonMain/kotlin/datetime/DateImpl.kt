@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package datetime

import kotlinx.datetime.*
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
internal class DateImpl internal constructor(private val value: LocalDate) : Date {
    @JsName("of")
    constructor(year: Int, monthNumber: Int, dayOfMonth: Int) : this(LocalDate(year, monthNumber, dayOfMonth))

    companion object {
        fun parse(isoString: String) = DateImpl(LocalDate.parse(isoString))
        fun today(tz: TimeZone = TimeZone.UTC) = DateImpl(Clock.System.now().toLocalDateTime(tz).date)
    }

    override val year: Int get() = value.year
    override val monthNumber: Int get() = value.monthNumber
    override val month: Month get() = Month.valueOf(value.month.name)
    override val monthName get() = month.name
    override val dayOfMonth: Int get() = value.dayOfMonth
    override val dayOfWeek: DayOfWeek get() = DayOfWeek.valueOf(value.dayOfWeek.name)
    override val dayOfYear: Int get() = value.dayOfYear

    override fun format(pattern: String): String = DateFormatter(pattern).format(this)

    override fun minus(period: DatePeriod): Date = DateImpl(value.minus(period))

    override fun minus(other: Date): DatePeriod = value.minus(other.toLocalDate())

    override fun toIsoFormat(): String = format(Date.ISO_PATTER)

    override fun compareTo(other: Date): Int = value.compareTo(other.toLocalDate())

    override fun equals(other: Any?): Boolean = other is DateImpl && value == other.value

    override fun hashCode(): Int = value.hashCode()

    override fun toString() = value.toString()
}