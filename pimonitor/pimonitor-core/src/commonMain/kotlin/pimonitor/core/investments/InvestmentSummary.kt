@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.investments

import bitframe.core.UserRef
import datetime.Date
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.utils.disbursables.DisbursableSummary
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import kotlin.js.JsExport

@Serializable
data class InvestmentSummary(
    override val uid: String,
    override val owningSpaceId: String,
    override val businessId: String,
    override val businessName: String,
    override val name: String,
    val type: String,
    val source: String,
    override val amount: Money,
    val date: Date,
    val details: String,
    val history: List<InvestmentHistory>,
    override val disbursements: List<Disbursement>,
    val createdBy: UserRef,
    override val deleted: Boolean
) : DisbursableSummary() {
    override fun copy(disbursements: List<Disbursement>) = copy(uid = uid, disbursements = disbursements)
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}