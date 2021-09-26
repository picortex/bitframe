package bitframe.server.modules.authentication

import bitframe.authentication.signin.Basic
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.LoginCredentials
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
import later.Later
import later.await
import later.later

class DefaultAuthenticationService(
    override val usersDao: UsersDao,
    override val spacesDao: SpacesDao
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
        val u = usersDao.create(userParams).await()
        val spaceParams = CreateSpaceParams(space.name)
        val s = spacesDao.createIfNotExist(spaceParams).await()
        LoginConundrum(u, listOf(s))
    }

    override fun signIn(credentials: LoginCredentials): Later<LoginConundrum> = later {
        val matches = usersDao.all(where = "contacts" contains credentials.alias).await()
        if (matches.isEmpty()) throw RuntimeException("User with alias=${credentials.alias}, not found")
        val match = matches.first()
        LoginConundrum(match,spaces = match.spaces)
    }

    override fun users(): Later<List<User>> {
        return usersDao.all()
    }

    override fun spaces(): Later<List<Space>> {
        return spacesDao.all()
    }
}