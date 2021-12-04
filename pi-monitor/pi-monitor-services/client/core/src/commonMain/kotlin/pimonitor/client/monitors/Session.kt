@file:JsExport

package pimonitor.client.monitors

import pimonitor.monitors.Monitor
import kotlin.js.JsExport

sealed class Session {
    object Unknown : Session()
    data class Active(val monitor: Monitor) : Session()

    fun currentMonitorOrThrow() = when (this) {
        Unknown -> throw RuntimeException("Monitor session is not yet, known. Are you logged in properly")
        is Active -> monitor
    }

    fun currentMonitorOrNull() = when (this) {
        Unknown -> null
        is Active -> monitor
    }
}
