@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.investments

import akkounts.utils.unset
import bitframe.core.Savable
import bitframe.core.UserRef
import datetime.Date
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.business.utils.disbursements.Disbursement
import presenters.numerics.Percentage
import kotlin.js.JsExport

@Serializable
data class InvestmentSummary(
    val uid: String = unset,
    val businessId: String,
    val businessName: String,
    val name: String,
    val type: String,
    val source: String,
    val amount: Money,
    val date: Date,
    val details: String,
    val history: List<InvestmentHistory>,
    val disbursements: List<Disbursement>,
    val createdBy: UserRef,
    val totalDisbursed: Money,
    val disbursementProgressInPercentage: Percentage
)