@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.changes

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
    current: Money,
    increaseFeeling: ChangeFeeling? = null,
    decreaseFeeling: ChangeFeeling? = null,
    fixedFeeling: ChangeFeeling? = null
): ChangeRemark<Money> {
    if (previous.currency != current.currency) {
        return ChangeRemark.Indeterminate
    }
    val diff = current - previous

    return when {
        previous.amount == 0 && diff.amount < 0 -> ChangeRemark.Decrease(
            pct = Percentage.ONE_HUNDRED,
            value = diff * -1,
            feeling = decreaseFeeling ?: ChangeRemark.Decrease.DEFAULT_FEELING
        )
        previous.amount != 0 && diff.amount < 0 -> ChangeRemark.Decrease(
            pct = Percentage.fromRatio(diff.amount * -1.0 / previous.amount),
            value = diff * -1,
            feeling = decreaseFeeling ?: ChangeRemark.Decrease.DEFAULT_FEELING
        )
        previous.amount == 0 && diff.amount > 0 -> ChangeRemark.Increase(
            pct = Percentage.ONE_HUNDRED,
            value = diff,
            feeling = increaseFeeling ?: ChangeRemark.Increase.DEFAULT_FEELING
        )
        previous.amount != 0 && diff.amount > 0 -> ChangeRemark.Increase(
            pct = Percentage.fromRatio(diff.amount.toDouble() / previous.amount),
            value = diff,
            feeling = increaseFeeling ?: ChangeRemark.Increase.DEFAULT_FEELING
        )
        else -> ChangeRemark.Fixed(
            at = previous,
            feeling = fixedFeeling ?: ChangeRemark.Fixed.DEFAULT_FEELING
        )
    }
}

fun changeRemarkOf(
    previous: Number,
    current: Number,
    increaseFeeling: ChangeFeeling? = null,
    decreaseFeeling: ChangeFeeling? = null,
    fixedFeeling: ChangeFeeling? = null
): ChangeRemark<Double> {
    val prev = previous.toDouble()
    val diff = current.toDouble() - prev

    return when {
        prev == 0.0 && diff < 0.0 -> ChangeRemark.Decrease(
            pct = Percentage.ONE_HUNDRED,
            value = diff * -1,
            feeling = decreaseFeeling ?: ChangeRemark.Decrease.DEFAULT_FEELING
        )
        prev != 0.0 && diff < 0.0 -> ChangeRemark.Decrease(
            pct = Percentage.fromRatio(-diff / prev),
            value = diff * -1,
            feeling = decreaseFeeling ?: ChangeRemark.Decrease.DEFAULT_FEELING
        )
        prev == 0.0 && diff > 0.0 -> ChangeRemark.Increase(
            pct = Percentage.ONE_HUNDRED,
            value = diff,
            feeling = increaseFeeling ?: ChangeRemark.Increase.DEFAULT_FEELING
        )
        prev != 0.0 && diff > 0.0 -> ChangeRemark.Increase(
            pct = Percentage.fromRatio(diff / prev),
            value = diff,
            feeling = increaseFeeling ?: ChangeRemark.Increase.DEFAULT_FEELING
        )
        else -> ChangeRemark.Fixed(
            at = prev,
            feeling = fixedFeeling ?: ChangeRemark.Fixed.DEFAULT_FEELING
        )
    }
}