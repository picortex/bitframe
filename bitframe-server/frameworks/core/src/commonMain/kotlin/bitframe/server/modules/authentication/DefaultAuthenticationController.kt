package bitframe.server.modules.authentication

class DefaultAuthenticationController(
    override val service: AuthenticationService
) : AuthenticationController