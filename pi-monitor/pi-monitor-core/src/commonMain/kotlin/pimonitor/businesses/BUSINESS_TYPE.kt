@file:JsExport

package pimonitor.businesses

import kotlin.js.JsExport
import kotlin.jvm.JvmField

object BUSINESS_TYPE {
    @JvmField
    val Monitor = PiMonitorBusinessType.Monitor

    @JvmField
    val Monitored = PiMonitorBusinessType.Monitored
}