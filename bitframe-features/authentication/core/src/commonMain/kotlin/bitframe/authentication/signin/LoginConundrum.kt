@file:JsExport

package bitframe.authentication.signin

import bitframe.actors.spaces.Space
import bitframe.actors.users.User
import kotlinx.collections.interoperable.List
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class LoginConundrum(
    val user: User,
    val spaces: List<Space>
)