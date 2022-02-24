package bitframe.server.signin

import bitframe.core.signin.SignInDaodUseCase
import bitframe.core.signin.SignInService as CoreSignInService
import bitframe.core.signin.SignInUseCase
import bitframe.server.ServiceConfig

class SignInService(
    val config: ServiceConfig
) : CoreSignInService(), SignInUseCase by SignInDaodUseCase(config)