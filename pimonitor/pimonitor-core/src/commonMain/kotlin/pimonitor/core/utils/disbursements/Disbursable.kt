@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.utils.disbursements

import bitframe.core.Savable
import kash.Money
import kotlinx.collections.interoperable.List
import pimonitor.core.business.utils.money.sum
import presenters.numerics.Percentage
import kotlin.js.JsExport

abstract class Disbursable : Savable {
    abstract val amount: Money
    abstract val disbursements: List<Disbursement>

    val totalDisbursed by lazy { disbursements.filter { !it.deleted }.map { it.amount }.sum(amount.currency) }

    val disbursementProgressInPercentage by lazy {
        Percentage.fromRatio(totalDisbursed.amount / amount.amount)
    }
}