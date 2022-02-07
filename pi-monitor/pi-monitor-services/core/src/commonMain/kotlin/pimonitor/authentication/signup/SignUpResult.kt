@file:JsExport

package pimonitor.authentication.signup

import bitframe.actors.apps.App
import bitframe.actors.spaces.Space
import bitframe.actors.users.User
import kotlinx.serialization.Serializable
import pimonitor.monitors.Monitor
import kotlin.js.JsExport

@Serializable
data class SignUpResult(
    val app: App,
    val space: Space,
    val user: User,
    val monitor: Monitor
)