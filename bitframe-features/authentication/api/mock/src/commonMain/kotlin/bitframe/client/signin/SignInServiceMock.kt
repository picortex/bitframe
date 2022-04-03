package bitframe.client.signin

import bitframe.client.ServiceConfigMock
import bitframe.core.signin.SignInServiceCore
import bitframe.core.signin.SignInServiceDaod

open class SignInServiceMock(
    private val config: ServiceConfigMock = ServiceConfigMock()
) : SignInService(config), SignInServiceCore by SignInServiceDaod(config)