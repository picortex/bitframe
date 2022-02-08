package bitframe.authentication.server.users

import bitframe.actors.spaces.Space
import bitframe.actors.users.User
import bitframe.actors.users.UsersService
import bitframe.actors.users.usecases.RegisterUserUseCase
import bitframe.authentication.server.spaces.usecases.CreateSpaceIfNotExist
import bitframe.authentication.service.daod.usecase.RegisterUserUseCaseImpl
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.authentication.users.CreateUserParams
import bitframe.authentication.users.toUser
import bitframe.daos.conditions.isEqualTo
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import later.await
import later.later

class UsersService(
    private val config: ServiceConfig
) : UsersService(),
    CreateSpaceIfNotExist by CreateSpaceIfNotExist(config),
    RegisterUserUseCase by RegisterUserUseCaseImpl(config) {
    private val spacesDao = config.daoFactory.get<Space>()
    private val usersDao = config.daoFactory.get<User>()
    private val scope = config.scope

    fun createUserIfNotExist(params: CreateUserParams) = scope.later {
        val existing = usersDao.all(condition = "name" isEqualTo params.name).await().firstOrNull()
        existing ?: usersDao.create(params.toUser("")).await()
    }

    override fun createIfNotExist(params: CreateUserParams) = scope.later {
        val accountParams = CreateSpaceParams("Genesis", "genesis", "genesis")
        val account = createSpaceIfNotExist(accountParams)
        val user = createUserIfNotExist(params)
        account.await(); user.await()
    }
}