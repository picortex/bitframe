package pimonitor

import bitframe.BrowserApplication
import pimonitor.screens.authentication.SignUpScreen

interface PiMonitorBrowserApplication : BrowserApplication {
    suspend fun openSignUpScreen(): SignUpScreen
}