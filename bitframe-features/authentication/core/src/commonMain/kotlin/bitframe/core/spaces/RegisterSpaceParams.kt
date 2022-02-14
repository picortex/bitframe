@file:JsExport

package bitframe.core.spaces

import bitframe.core.UserRef
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class RegisterSpaceParams(
    val name: String,
    val userRef: UserRef
)
