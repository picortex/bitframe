@file:JsExport

package pimonitor.authentication.signup

import bitframe.core.App
import bitframe.core.actors.spaces.Space
import bitframe.core.User
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class SignUpResult(
    val app: App,
    val space: Space,
    val user: User
)