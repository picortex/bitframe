package pimonitor.screens.api

interface Screen {
    suspend fun isVisible(): Boolean
}