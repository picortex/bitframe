@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.interventions

import datetime.Date
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.utils.disbursables.DisbursableSummary
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import kotlin.js.JsExport

@Serializable
data class InterventionSummary(
    override val uid: String,
    override val owningSpaceId: String,
    override val businessId: String,
    override val businessName: String,
    override val name: String,
    override val amount: Money,
    val date: Date,
    val deadline: Date,
    val recommendations: String,
    val goals: List<String>,
    val history: List<InterventionHistory>,
    override val disbursements: List<Disbursement>,
    override val deleted: Boolean
) : DisbursableSummary() {
    val createdBy by lazy { history.filterIsInstance<InterventionHistory.Created>().first().by }
    override fun copy(disbursements: List<Disbursement>) = copy(uid = uid, disbursements = disbursements)
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}