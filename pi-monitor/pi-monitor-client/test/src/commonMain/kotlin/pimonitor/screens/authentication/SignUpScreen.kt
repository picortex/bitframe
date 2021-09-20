package pimonitor.screens.authentication

import pimonitor.MonitorParams
import pimonitor.screens.api.Screen

interface SignUpScreen : Screen {
    suspend fun signUpIndividuallyAs(person: MonitorParams)
    suspend fun signUpAs(person: MonitorParams, representing: MonitorParams)
    suspend fun expectUserToBeRegistered()
}