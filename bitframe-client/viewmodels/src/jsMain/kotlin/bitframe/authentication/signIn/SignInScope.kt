@file:JsExport

package bitframe.authentication.signIn

import bitframe.BitframeService
import bitframe.authentication.LoginCredentials
import bitframe.authentication.SignInService

external interface Credentials {
    var username: String
    var password: String
}

class SignInScope(service: SignInService) {

    val viewModel by lazy { SignInViewModel(service) }

    val submit = { cred: Credentials -> viewModel.post(submitIntent(cred)) }

    fun submitIntent(credentials: Credentials) = SignInIntent.Submit(
        LoginCredentials(
            username = credentials.username,
            password = credentials.password
        )
    )
}