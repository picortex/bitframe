package unit

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInService
import later.Later

class UnitTestSignInService : SignInService() {
    override fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum> {
        error("Don't call this")
    }

    override fun signIn(cred: SignInCredentials): Later<LoginConundrum> {
        error("Don't call this")
    }
}