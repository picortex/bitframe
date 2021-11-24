package bitframe.screens.authentication

import bitframe.screens.dashboard.DashboardScreen

interface SignUpProcess {
    fun expectToBeSigningUp() : DashboardScreen
}