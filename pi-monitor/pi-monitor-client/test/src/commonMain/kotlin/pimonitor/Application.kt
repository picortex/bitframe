package pimonitor

import pimonitor.screens.LandingScreen

interface Application {
    fun openLandingScreen(): LandingScreen
    fun expectUserToBeLoggedIn()
    fun expectUserToNotBeLoggedIn()
}