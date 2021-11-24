package bitframe.screens

import bitframe.screens.api.Screen
import bitframe.screens.authentication.SignInScreen
import bitframe.screens.authentication.SignUpScreen

interface LandingScreen : Screen {
    fun clickSignInButton(): SignInScreen
    fun clickSignUpButton(): SignUpScreen
}