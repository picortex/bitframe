package pimonitor.core.business.overview

import kotlinx.serialization.Serializable
import presenters.charts.Chart

@Serializable
data class BalanceSheetChart(
    val assets: Chart<Double>,
    val equityPlusLiabilities: Chart<Double>
)