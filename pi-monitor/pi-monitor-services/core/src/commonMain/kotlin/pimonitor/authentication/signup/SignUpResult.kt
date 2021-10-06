@file:JsExport

package pimonitor.authentication.signup

import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import kotlinx.serialization.Serializable
import pimonitor.monitors.Monitor
import kotlin.js.JsExport

@Serializable
class SignUpResult(
    val app: App,
    val space: Space,
    val user: User,
    val monitor: Monitor
)