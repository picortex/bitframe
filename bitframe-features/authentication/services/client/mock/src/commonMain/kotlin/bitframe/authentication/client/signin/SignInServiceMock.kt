package bitframe.authentication.client.signin

import bitframe.authentication.service.daod.usecase.SignInDaodUseCase
import bitframe.authentication.signin.SignInUseCase
import bitframe.service.client.config.MockServiceConfig

open class SignInServiceMock(
    override val config: MockServiceConfig = MockServiceConfig()
) : SignInService(config), SignInUseCase by SignInDaodUseCase(config)