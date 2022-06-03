package authenticator

import bitframe.core.signin.SignInParams
import bitframe.core.signin.SignInResult
import later.Later

class AuthenticatorApiImpl(
    val services: AuthenticatorServices
) : AuthenticatorApi {
    override val session: SessionAware get() = SessionAwareImpl(services.config)
    override fun signIn(params: SignInParams): Later<SignInResult> = services.signIn.signIn(params)
    override fun signOut() = services.signOut.signOut()
    override val users: UsersService get() = services.users
}