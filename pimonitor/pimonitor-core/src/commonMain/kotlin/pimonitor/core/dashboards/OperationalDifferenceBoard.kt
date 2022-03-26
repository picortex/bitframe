@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.dashboards

import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.Serializable
import presenters.changes.MoneyChangeBox
import presenters.changes.NumberChangeBox
import presenters.charts.Chart
import kotlin.js.JsExport

@Serializable
data class OperationalDifferenceBoard(
    val moneyCards: List<MoneyChangeBox>,
    val numberCards: List<NumberChangeBox>,
    val charts: List<Chart<Double>>
) {
    val cards by lazy {
        (moneyCards + numberCards).sortedBy { it.priority }.toInteroperableList()
    }
}