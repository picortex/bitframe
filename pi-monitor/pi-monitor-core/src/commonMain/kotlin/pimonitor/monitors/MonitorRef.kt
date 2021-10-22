@file:JsExport

package pimonitor.monitors

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class MonitorRef(
    val uid: String,
    val name: String
)