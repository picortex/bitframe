package bitframe.screens.api

interface Screen {
    suspend fun isVisible(): Boolean
}