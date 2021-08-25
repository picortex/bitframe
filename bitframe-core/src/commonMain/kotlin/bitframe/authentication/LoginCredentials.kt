package bitframe.authentication

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@JsExport
@Serializable
data class LoginCredentials(
    val username: String,
    val password: String
)