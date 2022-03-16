package akkounts.regulation

import kotlinx.datetime.toDateTimePeriod
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime
import kotlin.time.toDuration

@OptIn(ExperimentalTime::class)
class Period(
    val scalar: Double,
    val unit: DurationUnit
) {
    companion object {
        @JvmField
        val INFINITE = Period(Double.MAX_VALUE, DurationUnit.DAYS)
    }

    val dateTimePeriod get() = duration.toDateTimePeriod()
    val duration get() = scalar.toDuration(unit)
}