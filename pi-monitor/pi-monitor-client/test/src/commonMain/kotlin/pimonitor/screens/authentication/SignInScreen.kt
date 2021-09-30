package pimonitor.screens.authentication

import bitframe.authentication.signin.SignInCredentials
import pimonitor.screens.api.Screen

interface SignInScreen : Screen {
    suspend fun isShowingInvalidCredentials(): Boolean
    suspend fun loginWith(credentials: SignInCredentials)
}