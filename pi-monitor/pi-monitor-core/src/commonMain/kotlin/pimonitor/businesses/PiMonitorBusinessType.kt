@file:JsExport

package pimonitor.businesses

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class PiMonitorBusinessType {

    @Serializable
    object Monitor : PiMonitorBusinessType()

    @Serializable
    object Monitored : PiMonitorBusinessType()
}