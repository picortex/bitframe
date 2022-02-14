@file:JsExport

package pimonitor.core.spaces

import bitframe.core.SpaceType
import kotlin.js.JsExport
import kotlin.jvm.JvmField

object SPACE_TYPE {
    @JvmField
    val COOPERATE_MONITOR: SpaceType = "PIMONITOR_COOPERATE_MONITOR"

    @JvmField
    val INDIVIDUAL_MONITOR: SpaceType = "PIMONITOR_INDIVIDUAL_MONITOR"

    @JvmField
    val MONITORED: SpaceType = "PIMONITOR_MONITORED"

    @JvmField
    val GOVERNOR: SpaceType = "PIMONITOR_GOVERNOR"
}