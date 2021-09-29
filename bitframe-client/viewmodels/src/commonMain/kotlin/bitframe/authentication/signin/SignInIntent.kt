@file:JsExport

package bitframe.authentication.signin

import kotlin.js.JsExport

sealed class SignInIntent {
    data class ShowForm(val credentials: LoginCredentials?) : SignInIntent()
    data class Submit(val credentials: LoginCredentials) : SignInIntent()
}