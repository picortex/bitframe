@file:JsExport

package pimonitor.users

import bitframe.actors.users.UserType
import kotlin.js.JsExport
import kotlin.jvm.JvmField

object USER_TYPE {
    @JvmField
    val MONITOR: UserType = "PIMONITOR_MONITOR"

    @JvmField
    val CONTACT: UserType = "PIMONITOR_CONTACT"

    @JvmField
    val GOVERNOR: UserType = "PIMONITOR_GOVERNOR"
}