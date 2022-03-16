package bitframe.client.signin

import bitframe.core.signin.SignInDaodUseCase
import bitframe.client.ServiceConfigMock
import bitframe.core.signin.SignInUseCase

open class SignInServiceMock(
    override val config: ServiceConfigMock = ServiceConfigMock()
) : SignInService(config), SignInUseCase by SignInDaodUseCase(config)