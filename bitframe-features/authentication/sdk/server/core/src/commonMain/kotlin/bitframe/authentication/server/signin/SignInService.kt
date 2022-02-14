package bitframe.authentication.server.signin

import bitframe.core.signin.SignInDaodUseCase
import bitframe.authentication.signin.SignInUseCase
import bitframe.service.server.config.ServiceConfig

class SignInService(
    val config: ServiceConfig
) : SignInService(), SignInUseCase by SignInDaodUseCase(config)