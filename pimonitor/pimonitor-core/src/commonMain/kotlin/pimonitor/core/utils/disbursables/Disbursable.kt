@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.utils.disbursables

import bitframe.core.Savable
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.business.utils.money.sum
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import presenters.numerics.Percentage
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
abstract class Disbursable : Savable {
    abstract val businessId: String
    abstract val owningSpaceId: String
    abstract val name: String
    abstract val amount: Money
    abstract val disbursements: List<Disbursement>

    val totalDisbursed by lazy { disbursements.filter { !it.deleted }.map { it.amount }.sum(amount.currency) }

    val disbursementProgressInPercentage by lazy {
        Percentage.fromRatio(totalDisbursed.amount.toDouble() / amount.amount)
    }

    @JsName("_ignore_copyDisbursable")
    abstract fun copy(disbursements: List<Disbursement>): Disbursable
}