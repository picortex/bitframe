package bitframe.server.modules.authentication

import bitframe.authentication.signin.SignInService
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.authentication.users.UsersService
import bitframe.events.EventBus
import bitframe.server.data.DAOProvider
import bitframe.service.config.ServiceConfig
import cache.Cache

class AuthenticationServiceImpl(
    val bus: EventBus,
    cache: Cache,
    private val usersDao: UsersDao,
    private val spacesDao: SpacesDao,
    private val config: ServiceConfig,
    override val signIn: SignInService = SignInServiceImpl(usersDao, spacesDao, config, cache, bus),
    override val users: UsersService = UsersServiceImpl(usersDao, spacesDao, config)
) : AuthenticationService {
    constructor(provider: DAOProvider, config: ServiceConfig, cache: Cache, bus: EventBus) : this(bus, cache, provider.users, provider.spaces, config)
}