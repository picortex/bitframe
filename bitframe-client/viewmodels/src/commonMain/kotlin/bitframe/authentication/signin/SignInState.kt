@file:JsExport

package bitframe.authentication.signin

import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import kotlin.js.JsExport

sealed class SignInState {
    data class Form(val fields: SignInFormFields) : SignInState()
    data class Conundrum(val user: User, val spaces: List<Space>) : SignInState()
    data class Loading(val message: String) : SignInState()
    data class Failure(val cause: Throwable, val message: String? = cause.message) : SignInState()
    data class Success(val message: String) : SignInState()
}