package bitframe.client.users

import bitframe.core.users.UsersService
import bitframe.core.users.RegisterUserUseCase
import bitframe.core.users.RegisterUserUseCaseImpl
import bitframe.client.MockServiceConfig

class UsersServiceMock(
    private val config: MockServiceConfig
) : UsersService(), RegisterUserUseCase by RegisterUserUseCaseImpl(config)