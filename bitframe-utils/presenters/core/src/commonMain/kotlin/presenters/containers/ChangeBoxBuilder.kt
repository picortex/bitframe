@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.containers

import kash.Money
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmName

private const val MONEY_CHANGE_BOX_NAME = "moneyChangeBoxOf"

@JsName(MONEY_CHANGE_BOX_NAME)
fun changeBoxOf(
    previous: Money,
    current: Money,
    details: String = "Updated now"
): ChangeBox<Money> = ChangeBox(
    previous = previous,
    current = current,
    details = details,
    change = changeRemarkOf(previous, current)
)

fun <N : Number> changeBoxOf(
    previous: N,
    current: N,
    details: String = "Update now"
): ChangeBox<Double> = ChangeBox(
    previous = previous.toDouble(),
    current = current.toDouble(),
    details = details,
    change = changeRemarkOf(previous, current)
)