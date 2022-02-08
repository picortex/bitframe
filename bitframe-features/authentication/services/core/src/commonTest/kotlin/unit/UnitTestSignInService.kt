package unit

import bitframe.authentication.signin.SignInResult
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInService
import later.Later

class UnitTestSignInService : SignInService() {
    override fun executeSignIn(credentials: SignInCredentials): Later<SignInResult> {
        error("Don't call this")
    }

    override fun signIn(cred: SignInCredentials): Later<SignInResult> {
        error("Don't call this")
    }
}