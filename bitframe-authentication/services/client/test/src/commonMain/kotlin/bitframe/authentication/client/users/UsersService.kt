package bitframe.authentication.server.users

import bitframe.authentication.signin.Basic
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.users.CreateUserParams
import bitframe.authentication.users.RegisterUserParams
import bitframe.authentication.users.UsersService
import later.Later
import later.await
import later.later

class UsersService(
    private val config: UsersServiceConfig
) : UsersService() {
    private val spacesDao = config.spacesDao
    private val usersDao = config.usersDao
    private val scope = config.scope

    override fun createIfNotExist(params: CreateUserParams) = scope.later {
        val accountParams = CreateSpaceParams("Genesis")
        val account = spacesDao.createIfNotExist(accountParams)
        val user = usersDao.createIfNotExist(params)
        account.await(); user.await()
    }

    override fun registerWithSpace(
        user: RegisterUserParams,
        space: CreateSpaceParams
    ): Later<LoginConundrum> = scope.later {
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