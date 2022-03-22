@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package presenters.changes

import kash.Money
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class MoneyChangeBox(
    override val previous: Money,
    override val current: Money,
    override val details: String,
) : ChangeBox<Money>() {
    override val change: ChangeRemark<Money> by lazy { changeRemarkOf(previous, current) }
}