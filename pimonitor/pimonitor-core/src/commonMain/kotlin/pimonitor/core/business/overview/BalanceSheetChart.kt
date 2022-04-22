package pimonitor.core.business.overview

import kash.Money
import kotlinx.serialization.Serializable
import presenters.charts.Chart

@Serializable
data class BalanceSheetChart(
    val assets: Chart<Money>,
    val equityPlusLiabilities: Chart<Money>
)