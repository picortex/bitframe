package pimonitor.screens.authentication

import pimonitor.MonitorBusinessParams
import pimonitor.authentication.signup.IndividualRegistrationParams
import pimonitor.screens.api.Screen

interface SignUpScreen : Screen {
    suspend fun signUpIndividuallyAs(person: IndividualRegistrationParams)
    suspend fun signUpAs(person: IndividualRegistrationParams, representing: MonitorBusinessParams)
    suspend fun expectUserToBeRegistered()
}