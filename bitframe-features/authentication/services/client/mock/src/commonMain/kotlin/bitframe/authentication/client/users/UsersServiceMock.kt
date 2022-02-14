package bitframe.authentication.client.users

import bitframe.core.actors.users.UsersService
import bitframe.core.actors.users.usecases.RegisterUserUseCase
import bitframe.authentication.service.daod.usecase.RegisterUserUseCaseImpl
import bitframe.client.MockServiceConfig

class UsersServiceMock(
    private val config: MockServiceConfig
) : UsersService(), RegisterUserUseCase by RegisterUserUseCaseImpl(config)