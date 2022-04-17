@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.interventions

import akkounts.utils.unset
import bitframe.core.Savable
import datetime.Date
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import pimonitor.core.utils.disbursables.Disbursable
import kotlin.js.JsExport

@Serializable
data class Intervention(
    override val uid: String = unset,
    val businessId: String,
    override val name: String,
    override val amount: Money,
    val date: Date,
    val deadline: Date,
    val recommendations: String,
    val goals: List<String>,
    val history: List<InterventionHistory>,
    override val disbursements: List<Disbursement>,
    override val deleted: Boolean = false
) : Disbursable(), Savable {
    val createdBy by lazy { history.filterIsInstance<InterventionHistory.Created>().first().by }
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
    override fun copy(disbursements: List<Disbursement>) = copy(uid = uid, disbursements = disbursements)
}