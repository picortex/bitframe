@file:JsExport

package bitframe.authentication.spaces

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class CreateSpaceParams(
    val name: String
)