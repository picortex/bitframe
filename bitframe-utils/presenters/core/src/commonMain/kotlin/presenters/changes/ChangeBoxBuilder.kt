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
    fixedFeeling: ChangeFeeling? = null
): ChangeBox<Money> = MoneyChangeBox(
    previous = previous,
    current = current,
    details = details,
    feeling = changeRemarkOf(previous, current, increaseFeeling, decreaseFeeling, fixedFeeling).feeling
)

fun <N : Number> numberChangeBoxOf(
    previous: N,
    current: N,
    details: String = "Update now",
    increaseFeeling: ChangeFeeling? = null,
    decreaseFeeling: ChangeFeeling? = null,
    fixedFeeling: ChangeFeeling? = null
): ChangeBox<N> = NumberChangeBox(
    previous = previous,
    current = current,
    details = details,
    feeling = changeRemarkOf(previous, current, increaseFeeling, decreaseFeeling, fixedFeeling).feeling
)

inline fun <T> genericChangeBoxOf(
    previous: T,
    current: T,
    details: String,
): ChangeBox<T> = GenericChangeBox(
    previous = previous,
    current = current,
    details = details,
    feeling = ChangeFeeling.Unknown
)

inline fun <reified T> changeBoxOf(
    previous: T,
    current: T,
    details: String,
    increaseFeeling: ChangeFeeling? = null,
    decreaseFeeling: ChangeFeeling? = null,
    fixedFeeling: ChangeFeeling? = null
): ChangeBox<T> = when {
    previous is Money && current is Money -> moneyChangeBoxOf(previous, current, details, increaseFeeling, decreaseFeeling, fixedFeeling) as ChangeBox<T>
    previous is Number && current is Number -> numberChangeBoxOf(previous, current, details, increaseFeeling, decreaseFeeling, fixedFeeling)
    else -> genericChangeBoxOf(previous, current, details)
}