package bitframe.authentication.signin

import bitframe.authentication.config.ServiceConfig
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.daos.conditions.contains
import later.Later
import later.await
import later.later

class SignInServiceImpl(
    private val usersDao: UsersDao,
    private val spacesDao: SpacesDao,
    private val config: ServiceConfig
) : SignInService() {
    private val scope = config.scope
    override fun signIn(credentials: LoginCredentials): Later<LoginConundrum> = scope.later {
        val matches = usersDao.all(where = "contacts" contains credentials.alias).await()
        if (matches.isEmpty()) throw RuntimeException("User with loginId=${credentials.alias}, not found")
        val match = matches.first()
        LoginConundrum(
            user = match,
            spaces = match.spaces
        )
    }
}