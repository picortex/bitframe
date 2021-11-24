package bitframe.screens.authentication

import bitframe.authentication.signup.SignUpParams
import bitframe.screens.api.Screen

interface SignUpScreen : Screen {
    suspend fun signUp(with: SignUpParams): SignUpProcess
    suspend fun expectUserToBeRegistered()
    suspend fun expectToBeSigningUp()
}