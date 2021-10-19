package pimonitor.screens.authentication

import pimonitor.screens.dashboard.DashboardScreen

interface SignUpProcess {
    fun expectToBeSigningUp() : DashboardScreen
}