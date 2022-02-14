package bitframe.client.signin

import bitframe.core.signin.SignInDaodUseCase
import bitframe.client.MockServiceConfig
import bitframe.core.signin.SignInUseCase

open class SignInServiceMock(
    override val config: MockServiceConfig = MockServiceConfig()
) : SignInService(config), SignInUseCase by SignInDaodUseCase(config)