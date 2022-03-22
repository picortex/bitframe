package presenters.changes

import kash.Money
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmName

fun moneyChangeBoxOf(
    previous: Money,
    current: Money,
    details: String = "Updated now"
): ChangeBox<Money> = MoneyChangeBox(
    previous = previous,
    current = current,
    details = details
)

fun <N : Number> numberChangeBoxOf(
    previous: N,
    current: N,
    details: String = "Update now"
): ChangeBox<N> = NumberChangeBox(
    previous = previous,
    current = current,
    details = details
)

inline fun <reified T> changeBoxOf(
    previous: T,
    current: T,
    details: String
): ChangeBox<T> = when {
    previous is Money && current is Money -> moneyChangeBoxOf(previous, current, details) as ChangeBox<T>
    previous is Number && current is Number -> numberChangeBoxOf(previous, current, details)
    else -> GenericChangeBox(previous, current, details) as ChangeBox<T>
}