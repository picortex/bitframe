@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.dashboards

import kash.Money
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.Serializable
import presenters.cards.ValueCard
import presenters.changes.ChangeBox
import presenters.charts.Chart
import kotlin.js.JsExport

@Serializable
data class OperationalDifferenceBoard(
    internal val moneyCards: List<ChangeBox<Money>>,
    internal val numberCards: List<ChangeBox<Double>>,
    val charts: List<Chart<Double>>
) {
    val cards by lazy {
        (moneyCards + numberCards).sortedBy { it.priority }.toInteroperableList()
    }
}