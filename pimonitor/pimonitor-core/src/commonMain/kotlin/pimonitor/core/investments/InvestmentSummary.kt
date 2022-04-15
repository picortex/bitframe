@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.investments

import bitframe.core.UserRef
import datetime.Date
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.utils.disbursements.Disbursable
import pimonitor.core.utils.disbursements.Disbursement
import kotlin.js.JsExport

@Serializable
data class InvestmentSummary(
    override val uid: String,
    val businessId: String,
    val businessName: String,
    val name: String,
    val type: String,
    val source: String,
    override val amount: Money,
    val date: Date,
    val details: String,
    val history: List<InvestmentHistory>,
    override val disbursements: List<Disbursement>,
    val createdBy: UserRef,
    override val deleted: Boolean
) : Disbursable() {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}