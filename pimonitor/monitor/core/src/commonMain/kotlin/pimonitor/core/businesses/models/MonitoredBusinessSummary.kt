@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.businesses.models

import bitframe.core.UserContact
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.serialization.Serializable
import pimonitor.core.businesses.DASHBOARD_FINANCIAL
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.DashboardFinancial
import pimonitor.core.businesses.DashboardOperational
import pimonitor.core.invites.Invite
import presenters.changes.ChangeBox
import presenters.containers.DetailBox
import kotlin.js.JsExport

@Serializable
data class MonitoredBusinessSummary(
    val uid: String,
    val name: String,
    val interventions: String,
    val operationalBoard: DashboardOperational = DASHBOARD_OPERATIONAL.NONE,
    val financialBoard: DashboardFinancial = DASHBOARD_FINANCIAL.NONE,
    val contacts: List<UserContact> = emptyList(),
    val invites: List<Invite> = emptyList(),
    val revenue: ChangeBox<Money>? = null,
    val expenses: ChangeBox<Money>? = null,
    val grossProfit: ChangeBox<Money>? = null,
    val velocity: DetailBox<String>? = null,
    val netCashFlow: ChangeBox<Money>? = null,
)