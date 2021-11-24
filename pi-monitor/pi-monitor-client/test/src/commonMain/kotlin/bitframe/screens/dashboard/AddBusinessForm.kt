package bitframe.screens.dashboard

import bitframe.monitored.CreateMonitoredBusinessParams

interface AddBusinessForm {
    suspend fun enter(details: CreateMonitoredBusinessParams)
    suspend fun submitByPressingEnter()
    suspend fun submitByClicking()
}
