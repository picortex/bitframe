@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.business.overview

import kash.Money
import kotlinx.serialization.Serializable
import presenters.charts.Chart
import kotlin.js.JsExport

@Serializable
data class BalanceSheetChart(
    val assets: Chart<Money>,
    val equityPlusLiabilities: Chart<Money>
)