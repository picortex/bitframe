package bitframe.client.users

import bitframe.core.users.UsersService
import bitframe.core.users.RegisterUserUseCase
import bitframe.core.users.RegisterUserUseCaseImpl
import bitframe.client.ServiceConfigMock

class UsersServiceMock(
    private val config: ServiceConfigMock
) : UsersService(), RegisterUserUseCase by RegisterUserUseCaseImpl(config)