package bitframe

import bitframe.screens.LandingScreen
import bitframe.screens.authentication.SignUpScreen

interface Application {
    suspend fun openLandingScreen(): LandingScreen
    suspend fun openSignUpScreen(): SignUpScreen
    suspend fun expectUserToBeLoggedIn()
    suspend fun expectUserToNotBeLoggedIn()
}