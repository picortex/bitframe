@file:JsExport

package pimonitor.client.monitors

import pimonitor.monitors.Monitor
import kotlin.js.JsExport

sealed class Session {
    object Unknown : Session()
    data class Active(val monitor: Monitor) : Session()
}
