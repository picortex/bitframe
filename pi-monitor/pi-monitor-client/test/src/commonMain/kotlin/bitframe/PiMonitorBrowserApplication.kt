package bitframe

import bitframe.screens.authentication.SignUpScreen

interface PiMonitorBrowserApplication : BrowserApplication {
    suspend fun openSignUpScreen(): SignUpScreen
}