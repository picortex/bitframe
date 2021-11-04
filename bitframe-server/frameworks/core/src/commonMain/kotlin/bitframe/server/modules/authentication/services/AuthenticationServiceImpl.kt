package bitframe.server.modules.authentication.services

import bitframe.authentication.server.signin.SignInService
import bitframe.authentication.server.users.UsersService
import bitframe.server.BitframeApplication
import bitframe.server.BitframeApplicationConfig
import bitframe.server.data.DAOProvider

class AuthenticationServiceImpl(
    config: AuthenticationServiceConfig
) : AuthenticationService {
    constructor(config: BitframeApplicationConfig<DAOProvider>) : this(AuthenticationServiceConfig(config))

    override val signIn: SignInService = SignInService(config)
    override val users: UsersService = UsersService(config)
}