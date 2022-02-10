@file:JsExport

package pimonitor.spaces

import kotlin.js.JsExport
import kotlin.jvm.JvmField

object SPACE_TYPE {
    @JvmField
    val CooperateMonitor = PiMonitorSpaceType.CooperateMonitor

    @JvmField
    val IndividualMonitor = PiMonitorSpaceType.IndividualMonitor

    @JvmField
    val Monitored = PiMonitorSpaceType.Monitored

    @JvmField
    val PiCortexGovernor = PiMonitorSpaceType.PiCortexGovernor
}