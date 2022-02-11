package bitframe.screens.authentication

import bitframe.screens.api.Screen
import bitframe.screens.dashboard.DashboardScreen

interface SignInScreen : Screen {
    suspend fun isShowingInvalidCredentials(): Boolean
    suspend fun signIn(credentials: SignInCredentials): DashboardScreen
}