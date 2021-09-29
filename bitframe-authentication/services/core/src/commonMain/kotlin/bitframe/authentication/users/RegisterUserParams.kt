@file:JsExport

package bitframe.authentication.users

import kotlin.js.JsExport

data class RegisterUserParams(
    val name: String,
    val contacts: Contacts,
    val password: String
)