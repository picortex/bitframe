@file:JsExport
package bitframe.authentication.signin

import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class LoginConundrum(
    val user: User,
    val spaces: List<Space>
)