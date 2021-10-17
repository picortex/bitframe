package pimonitor.screens.authentication

import pimonitor.authentication.signup.SignUpParams
import pimonitor.screens.api.Screen

interface SignUpScreen : Screen {
    suspend fun signUp(with: SignUpParams)
    suspend fun expectUserToBeRegistered()
}