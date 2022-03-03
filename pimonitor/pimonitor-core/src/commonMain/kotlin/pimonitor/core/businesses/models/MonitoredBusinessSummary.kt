@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.businesses.models

import bitframe.core.UserContact
import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import pimonitor.core.businesses.Dashboard
import pimonitor.core.invites.Invite
import presenters.containers.ChangeBox
import presenters.containers.DetailBox
import kotlin.js.JsExport

@Serializable
sealed class MonitoredBusinessSummary {
    abstract val uid: String
    abstract val name: String
    abstract val interventions: String
    abstract val invites: List<Invite>
    abstract val contacts: List<UserContact>

    @Serializable
    data class UnConnectedDashboard(
        override val uid: String,
        override val name: String,
        override val contacts: List<UserContact>,
        override val invites: List<Invite>,
        override val interventions: String
    ) : MonitoredBusinessSummary()

    @Serializable
    data class ConnectedDashboard(
        override val uid: String,
        override val name: String,
        val dashboard: Dashboard,
        override val contacts: List<UserContact>,
        override val invites: List<Invite>,
        val revenue: ChangeBox<Money>,
        val expenses: ChangeBox<Money>,
        val grossProfit: ChangeBox<Money>,
        val velocity: DetailBox<String>,
        val netCashFlow: ChangeBox<Money>,
        override val interventions: String
    ) : MonitoredBusinessSummary()
}