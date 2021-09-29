package pimonitor.screens.authentication

import bitframe.authentication.signin.LoginCredentials
import pimonitor.screens.api.Screen

interface SignInScreen : Screen {
    suspend fun isShowingInvalidCredentials(): Boolean
    suspend fun loginWith(credentials: LoginCredentials)
}