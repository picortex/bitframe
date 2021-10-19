package pimonitor.screens.dashboard

import pimonitor.monitored.CreateMonitoredBusinessParams

interface AddBusinessForm {
    suspend fun enter(details: CreateMonitoredBusinessParams)
    suspend fun submitByPressingEnter()
    suspend fun submitByClicking()
}
