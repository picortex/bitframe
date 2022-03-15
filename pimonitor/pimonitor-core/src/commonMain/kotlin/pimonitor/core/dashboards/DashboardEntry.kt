@file:JsExport

package pimonitor.core.dashboards

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class DashboardEntry(
    val title: String,
    val value: String = "",
)