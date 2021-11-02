package bitframe.authentication.signin

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDao
import bitframe.daos.conditions.contains
import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import cache.Cache
import later.Later
import later.await
import later.later

open class SignInServiceImpl(
    private val usersDao: UsersDao,
    private val spacesDao: SpacesDao,
    override val config: ServiceConfig,
    override val cache: Cache,
    override val bus: EventBus
) : SignInService(bus, cache, config) {
    constructor(provider: AuthenticationDaoProvider, config: ServiceConfig, cache: Cache, bus: EventBus) : this(provider.users, provider.spaces, config, cache, bus)

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
                Session.SignedIn(App(config.appId), it.spaces.first(), it.user)
            }
        }
    }
}