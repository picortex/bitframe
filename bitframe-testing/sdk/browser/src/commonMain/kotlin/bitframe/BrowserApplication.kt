package bitframe

import bitframe.screens.LandingScreen

interface BrowserApplication {
    suspend fun openLandingScreen(): LandingScreen
    suspend fun expectUserToBeLoggedIn()
    suspend fun expectUserToNotBeLoggedIn()
}