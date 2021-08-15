package bitframe.server.modules.authentication

import users.server.UsersService

class DefaultAuthenticationController(
    override val service: AuthenticationService
) : AuthenticationController