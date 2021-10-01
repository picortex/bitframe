@file:JsExport

package bitframe.authentication.users

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class RegisterUserParams(
    val name: String,
    val contacts: Contacts,
    val password: String
)