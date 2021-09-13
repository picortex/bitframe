@file:JsExport

package bitframe.authentication.signIn

import bitframe.authentication.LoginCredentials
import kotlin.js.JsExport

sealed class SignInIntent {
    data class ShowForm(val credentials: LoginCredentials?) : SignInIntent()
    data class Submit(val credentials: LoginCredentials) : SignInIntent()
}