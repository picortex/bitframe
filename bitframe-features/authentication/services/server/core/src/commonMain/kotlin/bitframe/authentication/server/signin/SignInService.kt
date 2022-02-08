package bitframe.authentication.server.signin

import bitframe.authentication.service.daod.usecase.SignInDaodUseCase
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInUseCase
import bitframe.service.server.config.ServiceConfig

class SignInService(
    val config: ServiceConfig
) : SignInService(), SignInUseCase by SignInDaodUseCase(config)