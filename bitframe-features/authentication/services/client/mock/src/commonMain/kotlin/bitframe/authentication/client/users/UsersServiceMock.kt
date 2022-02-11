package bitframe.authentication.client.users

import bitframe.actors.users.UsersService
import bitframe.actors.users.usecases.RegisterUserUseCase
import bitframe.authentication.service.daod.usecase.RegisterUserUseCaseImpl
import bitframe.service.client.config.MockServiceConfig

class UsersServiceMock(
    private val config: MockServiceConfig
) : UsersService(), RegisterUserUseCase by RegisterUserUseCaseImpl(config)