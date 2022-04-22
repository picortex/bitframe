@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.investments

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
data class Investment(
    override val uid: String = unset,
    override val businessId: String,
    override val owningSpaceId: String,
    override val name: String,
    val type: String,
    val source: String,
    override val amount: Money,
    val date: Date,
    val details: String,
    val history: List<InvestmentHistory>,
    override val disbursements: List<Disbursement>,
    override val deleted: Boolean = false
) : Disbursable() {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
    override fun copy(disbursements: List<Disbursement>) = copy(uid = uid, disbursements = disbursements)
    val createdBy by lazy { history.filterIsInstance<InvestmentHistory.Created>().first().by }
}