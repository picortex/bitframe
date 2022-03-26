package presenters.changes

import kash.Money
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmName

fun moneyChangeBoxOf(
    previous: Money,
    current: Money,
    details: String = "Updated now",
    increaseFeeling: ChangeFeeling? = null,
    decreaseFeeling: ChangeFeeling? = null,
    fixedFeeling: ChangeFeeling? = null,
    priority: Int = -1,
) = MoneyChangeBox(
    previous = previous,
    current = current,
    details = details,
    feeling = changeRemarkOf(previous, current, increaseFeeling, decreaseFeeling, fixedFeeling).feeling,
    priority
)

fun numberChangeBoxOf(
    previous: Number,
    current: Number,
    details: String = "Update now",
    increaseFeeling: ChangeFeeling? = null,
    decreaseFeeling: ChangeFeeling? = null,
    fixedFeeling: ChangeFeeling? = null,
    priority: Int = -1,
) = NumberChangeBox(
    previous = previous.toDouble(),
    current = current.toDouble(),
    details = details,
    feeling = changeRemarkOf(previous, current, increaseFeeling, decreaseFeeling, fixedFeeling).feeling,
    priority
)

inline fun <T> genericChangeBoxOf(
    previous: T,
    current: T,
    details: String,
    priority: Int = -1,
) = GenericChangeBox(
    previous = previous,
    current = current,
    details = details,
    feeling = ChangeFeeling.Unknown,
    priority
)

inline fun <reified T> changeBoxOf(
    previous: T,
    current: T,
    details: String,
    increaseFeeling: ChangeFeeling? = null,
    decreaseFeeling: ChangeFeeling? = null,
    fixedFeeling: ChangeFeeling? = null,
    priority: Int = -1,
): ChangeBox<T> = when {
    previous is Money && current is Money -> moneyChangeBoxOf(previous, current, details, increaseFeeling, decreaseFeeling, fixedFeeling, priority) as ChangeBox<T>
    previous is Number && current is Number -> numberChangeBoxOf(previous, current, details, increaseFeeling, decreaseFeeling, fixedFeeling, priority) as ChangeBox<T>
    else -> genericChangeBoxOf(previous, current, details, priority)
}