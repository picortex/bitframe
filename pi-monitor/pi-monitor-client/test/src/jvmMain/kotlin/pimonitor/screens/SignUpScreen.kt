package pimonitor.screens

import pimonitor.Monitor
import pimonitor.screens.authentication.SignUpScreen

class SignUpScreen : SignUpScreen {
    override suspend fun signUpAs(person: Monitor.Person, representing: Monitor.Business) {
        TODO("Not yet implemented")
    }

    override fun isVisible(): Boolean {
        TODO("Not yet implemented")
    }
}