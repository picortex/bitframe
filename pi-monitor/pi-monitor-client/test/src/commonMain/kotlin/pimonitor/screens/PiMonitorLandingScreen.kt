package pimonitor.screens

import bitframe.screens.LandingScreen
import pimonitor.screens.authentication.SignUpScreen

interface PiMonitorLandingScreen : LandingScreen {
    fun clickSignUpButton(): SignUpScreen
}