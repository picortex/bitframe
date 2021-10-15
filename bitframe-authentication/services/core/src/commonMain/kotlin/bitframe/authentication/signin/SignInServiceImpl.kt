package bitframe.authentication.signin

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDao
import bitframe.daos.conditions.contains
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import later.later

open class SignInServiceImpl(
    private val usersDao: UsersDao,
    private val spacesDao: SpacesDao,
    override val config: ServiceConfig
) : SignInService<Nothing?>() {
    constructor(provider: AuthenticationDaoProvider, config: ServiceConfig) : this(provider.users, provider.spaces, config)

    override fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        val matches = usersDao.all(where = "contacts" contains credentials.alias).await()
        if (matches.isEmpty()) throw RuntimeException("User with loginId=${credentials.alias}, not found")
        val match = matches.first()
        LoginConundrum(
            user = match,
            spaces = match.spaces
        ).also {
            session.value = if (it.spaces.size > 1) {
                Session.Conundrum(App(config.appId), it.spaces, it.user)
            } else {
                makeSession(App(config.appId), it.spaces.first(), it.user).await()
            }
        }
    }

    override fun makeSession(app: App, space: Space, user: User) = scope.later {
        Session.SignedIn(app, space, user, null)
    }
}