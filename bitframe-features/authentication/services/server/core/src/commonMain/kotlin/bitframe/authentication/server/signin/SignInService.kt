package bitframe.authentication.server.signin

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInService
import bitframe.actors.users.User
import bitframe.daos.conditions.contains
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import kotlinx.collections.interoperable.toInteroperableList
import later.Later
import later.await
import later.later

class SignInService(
    val config: ServiceConfig
) : SignInService() {

    private val scope get() = config.scope

    private val usersDao = config.daoFactory.get<User>()

    override fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        val matches = usersDao.all(condition = "contacts" contains credentials.identifier).await()
        if (matches.isEmpty()) throw RuntimeException("User with loginId=${credentials.identifier}, not found")
        val match = matches.first()
        LoginConundrum(
            user = match,
            spaces = match.spaces.toInteroperableList()
        )
    }

    override fun signIn(cred: SignInCredentials) = executeSignIn(validate(cred).getOrThrow())
}