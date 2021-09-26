@file:JsExport

package bitframe.authentication.signIn

import bitframe.authentication.LoginCredentials
import bitframe.authentication.signin.SignInService

external interface SignInCredentials {
    var username: String
    var password: String
}

class SignInScope(service: SignInService) {

    val viewModel by lazy { SignInViewModel(service) }

    val submit = { cred: SignInCredentials -> viewModel.post(submitIntent(cred)) }

    fun submitIntent(credentials: SignInCredentials) = SignInIntent.Submit(
        LoginCredentials(
            alias = credentials.username,
            password = credentials.password
        )
    )
}