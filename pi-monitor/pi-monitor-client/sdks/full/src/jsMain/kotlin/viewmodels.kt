@file:Suppress("EXPERIMENTAL_API_USAGE")

import bitframe.BitframeService
import bitframe.authentication.LoginCredentials
import bitframe.authentication.LoginViewModel

@JsExport
fun loginViewModel(service: BitframeService) = LoginViewModel(service.authentication)

external interface Credentials {
    var username: String
    var password: String
}

@JsExport
fun loginIntent(credentials: Credentials) = LoginViewModel.Intent.Login(
    LoginCredentials(
        username = credentials.username,
        password = credentials.password
    )
)