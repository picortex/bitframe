package pimonitor.screens.authentication

import pimonitor.screens.api.Screen

interface SignInScreen : Screen {
    suspend fun isShowingInvalidCredentials(): Boolean
    suspend fun loginWith(credentials: LoginCredentials)
}