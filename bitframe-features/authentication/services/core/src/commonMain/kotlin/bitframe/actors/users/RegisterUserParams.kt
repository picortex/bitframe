@file:JsExport

package bitframe.actors.users

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class RegisterUserParams(
    val name: String,
    val identifier: String,
    val password: String
)