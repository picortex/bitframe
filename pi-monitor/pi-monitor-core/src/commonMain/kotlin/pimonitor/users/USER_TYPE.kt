@file:JsExport

package pimonitor.users

import kotlin.js.JsExport
import kotlin.jvm.JvmField

object USER_TYPE {
    @JvmField
    val Monitor = PiMonitorUserType.Monitor

    @JvmField
    val Contact = PiMonitorUserType.Contact

    @JvmField
    val Governor = PiMonitorUserType.Governor
}