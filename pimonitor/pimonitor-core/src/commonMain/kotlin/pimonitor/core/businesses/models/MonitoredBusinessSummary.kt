@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.businesses.models

import kash.Money
import kotlinx.serialization.Serializable
import pimonitor.core.businesses.Dashboard
import presenters.containers.ChangeBox
import presenters.containers.DetailBox
import kotlin.js.JsExport

@Serializable
sealed class MonitoredBusinessSummary {
    abstract val uid: String
    abstract val name: String
    abstract val interventions: String

    @Serializable
    data class UnConnectedDashboard(
        override val uid: String,
        override val name: String,
        override val interventions: String
    ) : MonitoredBusinessSummary()

    @Serializable
    data class ConnectedDashboard(
        override val uid: String,
        override val name: String,
        val dashboard: Dashboard,
        val revenue: ChangeBox<Money>,
        val expenses: ChangeBox<Money>,
        val grossProfit: ChangeBox<Money>,
        val velocity: DetailBox<String>,
        val netCashFlow: ChangeBox<Money>,
        override val interventions: String
    ) : MonitoredBusinessSummary()
}