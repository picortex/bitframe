@file:JsExport

package bitframe.core.actors.spaces

import bitframe.core.actors.users.UserRef
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class RegisterSpaceParams(
    val name: String,
    val userRef: UserRef
)
