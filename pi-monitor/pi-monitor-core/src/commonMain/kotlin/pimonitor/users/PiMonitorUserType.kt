@file:JsExport

package pimonitor.users

import bitframe.actors.users.UserType
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
sealed class PiMonitorUserType : UserType() {
    @Serializable
    object Monitor : PiMonitorUserType()

    @Serializable
    object Contact : PiMonitorUserType()

    @Serializable
    object Governor : PiMonitorUserType()
}