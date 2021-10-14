package bitframe.server.modules.authentication

import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.authentication.users.UsersService
import bitframe.authentication.users.UsersServiceImpl
import bitframe.server.data.DAOProvider
import bitframe.service.config.ServiceConfig

class AuthenticationServiceImpl(
    private val usersDao: UsersDao,
    private val spacesDao: SpacesDao,
    private val config: ServiceConfig,
    override val signIn: SignInService<*> = SignInServiceImpl(usersDao, spacesDao, config),
    override val users: UsersService = UsersServiceImpl(usersDao, spacesDao, config)
) : AuthenticationService {
    constructor(provider: DAOProvider, config: ServiceConfig) : this(provider.users, provider.spaces, config)
}