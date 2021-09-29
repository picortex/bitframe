package bitframe.server.modules.authentication

import bitframe.authentication.config.ServiceConfig
import bitframe.authentication.signin.*
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.spaces.RegisterSpaceParams
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.*
import bitframe.server.data.DAOProvider
import bitframe.server.data.contains
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.coroutineScope
import later.Later
import later.await
import later.later

class AuthenticationServiceImpl(
    private val usersDao: UsersDao,
    private val spacesDao: SpacesDao,
    override val signIn: SignInService = SignInServiceImpl(usersDao, spacesDao, ServiceConfig()),
    override val users: UsersService = UsersServiceImpl(usersDao, spacesDao)
) : AuthenticationService, CoroutineScope by CoroutineScope(SupervisorJob()) {

    constructor(provider: DAOProvider) : this(provider.users, provider.spaces)
}