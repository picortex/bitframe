@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.containers

import kash.Money
import kotlinx.coroutines.FlowPreview
import kotlinx.serialization.Serializable
import presenters.numerics.Percentage
import presenters.numerics.pct
import kotlin.js.JsExport
import kotlin.js.JsName

private const val CHANGE_MONEY_REMARK_OF = "changeMoneyRemarkOf"

@JsName(CHANGE_MONEY_REMARK_OF)
fun changeRemarkOf(
    previous: Money,
    current: Money
): ChangeRemark<Money> {
    if (previous.currency != current.currency) {
        return ChangeRemark.Indeterminate
    }
    val diff = current - previous

    return when {
        previous.amount == 0 && diff.amount < 0 -> ChangeRemark.Decrease(
            pct = Percentage.ONE_HUNDRED,
            value = diff * -1,
            decreased = true
        )
        previous.amount != 0 && diff.amount < 0 -> ChangeRemark.Decrease(
            pct = Percentage.fromRatio(diff.amount * -1.0 / previous.amount),
            value = diff * -1,
            decreased = true
        )
        previous.amount == 0 && diff.amount > 0 -> ChangeRemark.Increase(
            pct = Percentage.ONE_HUNDRED,
            value = diff,
            increased = true
        )
        previous.amount != 0 && diff.amount > 0 -> ChangeRemark.Increase(
            pct = Percentage.fromRatio(diff.amount.toDouble() / previous.amount),
            value = diff,
            increased = true
        )
        else -> ChangeRemark.Fixed(
            pct = Percentage.ZERO,
            at = previous,
            fixed = true
        )
    }
}

fun <N : Number> changeRemarkOf(
    previous: N,
    current: N
): ChangeRemark<Double> {
    val prev = previous.toDouble()
    val diff = current.toDouble() - prev

    return when {
        prev == 0.0 && diff < 0.0 -> ChangeRemark.Decrease(
            pct = Percentage.ONE_HUNDRED,
            value = diff * -1,
            decreased = true
        )
        prev != 0.0 && diff < 0.0 -> ChangeRemark.Decrease(
            pct = Percentage.fromRatio(-diff / prev),
            value = diff * -1,
            decreased = true
        )
        prev == 0.0 && diff > 0.0 -> ChangeRemark.Increase(
            pct = Percentage.ONE_HUNDRED,
            value = diff,
            increased = true
        )
        prev != 0.0 && diff > 0.0 -> ChangeRemark.Increase(
            pct = Percentage.fromRatio(diff / prev),
            value = diff,
            increased = true
        )
        else -> ChangeRemark.Fixed(
            pct = Percentage.ZERO,
            at = prev,
            fixed = true
        )
    }
}