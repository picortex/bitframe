package pimonitor.screens.authentication

import bitframe.authentication.signin.SignInCredentials
import pimonitor.screens.api.Screen
import pimonitor.screens.dashboard.DashboardScreen

interface SignInScreen : Screen {
    suspend fun isShowingInvalidCredentials(): Boolean
    suspend fun signIn(credentials: SignInCredentials): DashboardScreen
}