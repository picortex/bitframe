package bitframe.authentication.server.users

import bitframe.authentication.server.spaces.usecases.CreateSpaceIfNotExist
import bitframe.authentication.server.users.usecases.RegisterUserImpl
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.*
import bitframe.authentication.users.UsersService
import bitframe.authentication.users.usecases.RegisterUser
import bitframe.daos.conditions.isEqualTo
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import later.await
import later.later

class UsersService(
    private val config: ServiceConfig
) : UsersService(),
    CreateSpaceIfNotExist by CreateSpaceIfNotExist(config),
    RegisterUser by RegisterUserImpl(config) {
    private val spacesDao = config.daoFactory.get<Space>()
    private val usersDao = config.daoFactory.get<User>()
    private val scope = config.scope

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
}