package pimonitor.screens.authentication

import pimonitor.Monitor
import pimonitor.screens.api.Screen

interface SignUpScreen : Screen {
    suspend fun signUpAs(person: Monitor.Person, representing: Monitor.Business)
}