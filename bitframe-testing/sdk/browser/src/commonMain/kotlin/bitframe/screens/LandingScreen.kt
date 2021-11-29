package bitframe.screens

import bitframe.screens.api.Screen
import bitframe.screens.authentication.SignInScreen

interface LandingScreen : Screen {
    fun clickSignInButton(): SignInScreen
}