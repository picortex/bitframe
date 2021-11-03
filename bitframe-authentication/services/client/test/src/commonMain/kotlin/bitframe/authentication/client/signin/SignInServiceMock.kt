package bitframe.authentication.client.signin

import bitframe.authentication.apps.App
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.Session
import bitframe.authentication.signin.SignInCredentials
import bitframe.daos.conditions.contains
import later.Later
import later.later

open class SignInServiceMock(
    override val config: SignInServiceMockConfig = SignInServiceMockConfig()
) : SignInService(config) {
    override fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        val matches = config.users.filter { it.contacts.contains(credentials.alias) }
        if (matches.isEmpty()) throw RuntimeException("User with loginId=${credentials.alias}, not found")
        val match = matches.first()
        LoginConundrum(
            user = match,
            spaces = match.spaces
        ).also {
            session.value = if (it.spaces.size > 1) {
                Session.Conundrum(App(config.appId), it.spaces, it.user)
            } else {
                Session.SignedIn(App(config.appId), it.spaces.first(), it.user)
            }
        }
    }
}