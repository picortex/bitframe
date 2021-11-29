package bitframe.screens.dashboard

import bitframe.screens.api.Screen

interface DashboardScreen : Screen {
    suspend fun selectBusinesses(): BusinessesScreen
}