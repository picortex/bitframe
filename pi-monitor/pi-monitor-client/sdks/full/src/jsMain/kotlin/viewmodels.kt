@file:JsExport
@file:Suppress("EXPERIMENTAL_API_USAGE")

import bitframe.authentication.LoginCredentials
import bitframe.authentication.LoginViewModel
import pimonitor.PiMonitorService

fun loginViewModel(service: PiMonitorService) = LoginViewModel(service.signIn)

external interface Credentials {
    var username: String
    var password: String
}

fun loginIntent(credentials: Credentials) = LoginViewModel.Intent.Login(
    LoginCredentials(
        username = credentials.username,
        password = credentials.password
    )
)