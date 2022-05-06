@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.portfolio

import bitframe.core.Space
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.emptyList
import kotlinx.serialization.Serializable
import pimonitor.core.businesses.MonitoredBusinessColumns
import pimonitor.core.businesses.models.MonitoredBusinessSummary
import pimonitor.core.interventions.InterventionSummary
import pimonitor.core.interventions.InterventionsColumns
import pimonitor.core.investments.InvestmentSummary
import pimonitor.core.investments.InvestmentsColumns
import presenters.cards.ValueCard
import presenters.table.builders.tableOf
import kotlin.js.JsExport

@Serializable
data class MonitorPortfolio(
    val space: Space,
    val cards: List<ValueCard<String>> = emptyList(),
    val profileProgress: ProfileProgress = ProfileProgress(),
    val businesses: List<MonitoredBusinessSummary>,
    val investments: List<InvestmentSummary>,
    val interventions: List<InterventionSummary>
) {
    val businessesTable by lazy {
        tableOf(businesses) { MonitoredBusinessColumns() }
    }

    val investmentsTable by lazy {
        tableOf(investments) { InvestmentsColumns(showBusinessColumn = true) }
    }

    val interventionsTable by lazy {
        tableOf(interventions) { InterventionsColumns(showBusinessColumn = true) }
    }
}