@file:JsExport

package pimonitor.core.picortex

import kotlin.js.JsExport

data class DashboardEntry(
    val title: String,
    val value: String = "",
)