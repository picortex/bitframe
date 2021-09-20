@file:JsExport

package pimonitor

import kotlin.js.JsExport

sealed class MonitorType {
    object Individual : MonitorType()
    object Organisation : MonitorType()
}
