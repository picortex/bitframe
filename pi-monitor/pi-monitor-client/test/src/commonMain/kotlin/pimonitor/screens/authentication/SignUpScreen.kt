package pimonitor.screens.authentication

import pimonitor.authentication.signup.SignUpParams
import pimonitor.screens.api.Screen

interface SignUpScreen : Screen {
    suspend fun signUp(with: SignUpParams): SignUpProcess
    suspend fun expectUserToBeRegistered()
    suspend fun expectToBeSigningUp()
}