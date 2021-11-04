package bitframe.server.modules.authentication.services

import bitframe.authentication.server.signin.SignInService
import bitframe.authentication.server.users.UsersService
import bitframe.server.BitframeApplication
import bitframe.server.BitframeApplicationConfig

class AuthenticationServiceImpl(
    config: AuthenticationServiceConfig
) : AuthenticationService {
    constructor(config: BitframeApplicationConfig) : this(AuthenticationServiceConfig(config))

    override val signIn: SignInService = SignInService(config)
    override val users: UsersService = UsersService(config)
}