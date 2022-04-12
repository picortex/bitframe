@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.interventions

import akkounts.utils.unset
import bitframe.core.Savable
import datetime.Date
import datetime.SimpleDateTime
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.business.utils.disbursements.Disbursement
import pimonitor.core.business.utils.money.sum
import presenters.numerics.Percentage
import kotlin.js.JsExport

@Serializable
data class Intervention(
    override val uid: String = unset,
    val businessId: String,
    val name: String,
    val amount: Money,
    val date: Date,
    val deadline: Date,
    val recommendations: String,
    val goals: List<String>,
    val history: List<InterventionHistory>,
    val disbursements: List<Disbursement>,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)

    val createdBy by lazy { history.filterIsInstance<InterventionHistory.Created>().first().by }

    val totalDisbursed by lazy { disbursements.map { it.amount }.sum(amount.currency) }

    val disbursementProgressInPercentage by lazy {
        Percentage.fromRatio(totalDisbursed.amount / amount.amount)
    }
}