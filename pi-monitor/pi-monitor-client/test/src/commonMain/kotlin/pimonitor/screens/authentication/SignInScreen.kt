package pimonitor.screens.authentication

import bitframe.authentication.LoginCredentials
import pimonitor.screens.api.Screen

interface SignInScreen : Screen {
    suspend fun loginWith(credentials: LoginCredentials)
}