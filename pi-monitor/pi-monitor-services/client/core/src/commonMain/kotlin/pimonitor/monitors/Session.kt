@file:JsExport

package pimonitor.monitors

import kotlin.js.JsExport

sealed class Session {
    object Unknown : Session()
    data class Active(val monitor: Monitor) : Session()
}
