package bitframe.authentication.server.signin

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInService
import bitframe.daos.conditions.contains
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later

open class SignInService(
    open val config: SignInServiceConfig
) : SignInService() {

    private val scope get() = config.scope

    override fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        val matches = config.usersDao.all(where = "contacts" contains credentials.alias).await()
        if (matches.isEmpty()) throw RuntimeException("User with loginId=${credentials.alias}, not found")
        val match = matches.first()
        LoginConundrum(
            user = match,
            spaces = match.spaces.toInteroperableList()
        )
    }

    override fun signIn(cred: SignInCredentials) = executeSignIn(validate(cred).getOrThrow())
}