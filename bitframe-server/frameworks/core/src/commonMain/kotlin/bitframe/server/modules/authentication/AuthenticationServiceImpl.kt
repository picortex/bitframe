package bitframe.server.modules.authentication

import bitframe.authentication.config.ServiceConfig
import bitframe.authentication.signin.*
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.users.CreateUserParams
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDao
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
    override val signIn: SignInService = SignInServiceImpl(usersDao, spacesDao, ServiceConfig())
) : AuthenticationService, CoroutineScope by CoroutineScope(SupervisorJob()) {

    constructor(provider: DAOProvider) : this(provider.users, provider.spaces)

    override fun createDefaultUserIfNotExist(params: CreateUserParams) = later {
        val accountParams = CreateSpaceParams("Genesis")
        val account = spacesDao.createIfNotExist(accountParams)
        val user = usersDao.createIfNotExist(params)
        account.await(); user.await()
    }

    override fun registerUser(
        user: RegisterUserParams,
        space: RegisterSpaceParams
    ): Later<LoginConundrum> = later {
        val userParams = CreateUserParams(
            name = user.name,
            contacts = user.contacts,
            credentials = Basic(
                identity = user.contacts.firstValue(),
                password = user.password
            )
        )
        val spaceParams = CreateSpaceParams(space.name)
        val s = spacesDao.createIfNotExist(spaceParams).await()
        val u = usersDao.create(userParams).await()
        val usr = usersDao.update(u.copy(spaces = listOf(s))).await()
        LoginConundrum(usr, usr.spaces)
    }
}