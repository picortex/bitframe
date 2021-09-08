package pimonitor.screens.authentication

import bitframe.authentication.LoginCredentials
import pimonitor.screens.api.Screen

interface SignUpScreen : Screen {
    suspend fun signUpWith(credentials: LoginCredentials)
}