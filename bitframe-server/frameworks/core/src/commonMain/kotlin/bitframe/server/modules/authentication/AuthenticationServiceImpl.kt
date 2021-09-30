package bitframe.server.modules.authentication

import bitframe.authentication.config.ServiceConfig
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.UsersDao
import bitframe.authentication.users.UsersService
import bitframe.authentication.users.UsersServiceImpl
import bitframe.server.data.DAOProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class AuthenticationServiceImpl(
    private val usersDao: UsersDao,
    private val spacesDao: SpacesDao,
    override val signIn: SignInService = SignInServiceImpl(usersDao, spacesDao, ServiceConfig()),
    override val users: UsersService = UsersServiceImpl(usersDao, spacesDao)
) : AuthenticationService, CoroutineScope by CoroutineScope(SupervisorJob()) {

    constructor(provider: DAOProvider) : this(provider.users, provider.spaces)
}