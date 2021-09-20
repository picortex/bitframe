package pimonitor.screens.authentication

import pimonitor.MonitorBusinessParams
import pimonitor.MonitorParams
import pimonitor.MonitorPersonParams
import pimonitor.screens.api.Screen

interface SignUpScreen : Screen {
    suspend fun signUpIndividuallyAs(person: MonitorPersonParams)
    suspend fun signUpAs(person: MonitorPersonParams, representing: MonitorBusinessParams)
    suspend fun expectUserToBeRegistered()
}