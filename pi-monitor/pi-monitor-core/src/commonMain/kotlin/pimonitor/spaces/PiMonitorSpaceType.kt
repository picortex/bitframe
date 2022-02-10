@file:JsExport

package pimonitor.spaces

import bitframe.actors.spaces.SpaceType
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class PiMonitorSpaceType : SpaceType() {

    @Serializable
    object CooperateMonitor : PiMonitorSpaceType()

    @Serializable
    object IndividualMonitor : PiMonitorSpaceType()

    @Serializable
    object Monitored : PiMonitorSpaceType()

    @Serializable
    object PiCortexGovernor : PiMonitorSpaceType()
}