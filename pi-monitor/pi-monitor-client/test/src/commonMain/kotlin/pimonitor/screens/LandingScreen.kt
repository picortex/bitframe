package pimonitor.screens

import pimonitor.screens.api.Screen
import pimonitor.screens.authentication.SignInScreen
import pimonitor.screens.authentication.SignUpScreen

interface LandingScreen : Screen {
    fun clickSignInButton(): SignInScreen
    fun clickSignUpButton(): SignUpScreen
}