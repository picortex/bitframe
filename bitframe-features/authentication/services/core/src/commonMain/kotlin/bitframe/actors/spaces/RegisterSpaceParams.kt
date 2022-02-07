@file:JsExport

package bitframe.actors.spaces

import bitframe.actors.users.UserRef
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class RegisterSpaceParams(
    val name: String,
    val userRef: UserRef
)
