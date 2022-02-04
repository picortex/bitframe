package bitframe.authentication.server.users

import bitframe.authentication.signin.Basic
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.toSpace
import bitframe.authentication.users.*
import bitframe.authentication.users.UsersService
import bitframe.daos.conditions.isEqualTo
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import kotlinx.collections.interoperable.listOf
import later.Later
import later.await
import later.later

class UsersService(
    private val config: ServiceConfig
) : UsersService() {
    private val spacesDao = config.daoFactory.get<Space>()
    private val usersDao = config.daoFactory.get<User>()
    private val scope = config.scope

    fun createSpaceIfNotExist(params: CreateSpaceParams): Later<Space> = scope.later {
        val existing = spacesDao.all(condition = "name" isEqualTo params.name).await().firstOrNull()
        existing ?: spacesDao.create(params.toSpace("")).await()
    }

    fun createUserIfNotExist(params: CreateUserParams) = scope.later {
        val existing = usersDao.all(condition = "name" isEqualTo params.name).await().firstOrNull()
        existing ?: usersDao.create(params.toUser("")).await()
    }

    override fun createIfNotExist(params: CreateUserParams) = scope.later {
        val accountParams = CreateSpaceParams("Genesis")
        val account = createSpaceIfNotExist(accountParams)
        val user = createUserIfNotExist(params)
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
        val s = createSpaceIfNotExist(spaceParams).await()
        val u = usersDao.create(userParams.toUser("")).await()
        val usr = usersDao.update(u.copy(spaces = listOf(s))).await()
        LoginConundrum(usr, usr.spaces)
    }
}