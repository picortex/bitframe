@file:JsExport

package bitframe.authentication.signIn

import bitframe.authentication.Account
import bitframe.authentication.LoginCredentials
import bitframe.authentication.User
import kotlin.js.JsExport

sealed class SignInState {
    data class Form(val credentials: LoginCredentials?) : SignInState()
    data class Conundrum(val user: User, val accounts: List<Account>) : SignInState()
    data class Loading(val message: String) : SignInState()
    data class Failure(val cause: Throwable, val message: String? = cause.message) : SignInState()
    data class Success(val message: String) : SignInState()
}