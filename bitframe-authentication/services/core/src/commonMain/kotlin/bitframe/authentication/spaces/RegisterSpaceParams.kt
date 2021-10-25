@file:JsExport

package bitframe.authentication.spaces

import bitframe.authentication.users.UserRef
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class RegisterSpaceParams(
    val name: String,
    val userRef: UserRef
)
