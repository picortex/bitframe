@file:JsExport

package bitframe.authentication.spaces

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class RegisterSpaceParams(
    val name: String
)
