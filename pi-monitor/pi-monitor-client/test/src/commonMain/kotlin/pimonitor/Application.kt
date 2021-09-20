package pimonitor

import pimonitor.screens.LandingScreen
import pimonitor.screens.authentication.SignUpScreen

interface Application {
    suspend fun openLandingScreen(): LandingScreen
    suspend fun openSignUpScreen(): SignUpScreen
    suspend fun expectUserToBeLoggedIn()
    suspend fun expectUserToNotBeLoggedIn()
}