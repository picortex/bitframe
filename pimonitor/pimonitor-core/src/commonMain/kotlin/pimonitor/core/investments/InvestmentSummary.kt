@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.investments

import bitframe.core.UserRef
import datetime.Date
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.utils.disbursables.Disbursable
import pimonitor.core.utils.disbursables.disbursements.Disbursement
import kotlin.js.JsExport

@Serializable
data class InvestmentSummary(
    override val uid: String,
    val businessId: String,
    val businessName: String,
    override val name: String,
    val type: String,
    val source: String,
    override val amount: Money,
    val date: Date,
    val details: String,
    val history: List<InvestmentHistory>,
    override val disbursements: List<Disbursement>,
    val createdBy: UserRef
) : Disbursable() {
    override fun copy(disbursements: List<Disbursement>) = copy(uid = uid, disbursements = disbursements)
}