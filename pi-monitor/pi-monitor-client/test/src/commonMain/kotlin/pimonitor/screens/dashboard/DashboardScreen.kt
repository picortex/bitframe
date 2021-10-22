package pimonitor.screens.dashboard

import pimonitor.screens.api.Screen

interface DashboardScreen : Screen {
    suspend fun selectBusinesses(): BusinessesScreen
}