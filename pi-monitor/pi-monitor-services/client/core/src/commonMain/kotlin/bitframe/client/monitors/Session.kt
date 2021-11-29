@file:JsExport

package bitframe.client.monitors

import bitframe.monitors.Monitor
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
