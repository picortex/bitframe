package bitframe.authentication.users

import bitframe.authentication.signin.Basic
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.spaces.RegisterSpaceParams
import bitframe.authentication.spaces.SpacesDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import later.Later
import later.await
import later.later

class UsersServiceImpl(
    private val usersDao: UsersDao,
    private val spacesDao: SpacesDao
) : UsersService(), CoroutineScope by CoroutineScope(SupervisorJob()) {

    override fun createDefaultIfNotExist(params: CreateUserParams) = later {
        val accountParams = CreateSpaceParams("Genesis")
        val account = spacesDao.createIfNotExist(accountParams)
        val user = usersDao.createIfNotExist(params)
        account.await(); user.await()
    }

    override fun register(
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