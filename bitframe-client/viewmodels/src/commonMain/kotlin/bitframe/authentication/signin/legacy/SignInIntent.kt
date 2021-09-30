@file:JsExport

package bitframe.authentication.signin.legacy

import bitframe.authentication.signin.SignInCredentials
import kotlin.js.JsExport

sealed class SignInIntent {
    data class ShowForm(val credentials: SignInCredentials?) : SignInIntent()
    data class Submit(val credentials: SignInCredentials) : SignInIntent()
}