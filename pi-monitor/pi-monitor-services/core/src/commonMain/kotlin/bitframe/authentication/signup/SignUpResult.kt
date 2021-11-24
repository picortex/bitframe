@file:JsExport

package bitframe.authentication.signup

import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import kotlinx.serialization.Serializable
import bitframe.monitors.Monitor
import kotlin.js.JsExport

@Serializable
data class SignUpResult(
    val app: App,
    val space: Space,
    val user: User,
    val monitor: Monitor
)