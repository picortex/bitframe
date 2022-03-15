@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.dashboards

import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import presenters.charts.Chart
import kotlin.js.JsExport

@Serializable
data class OperationalDashboard(
    val charts: List<Chart>
)