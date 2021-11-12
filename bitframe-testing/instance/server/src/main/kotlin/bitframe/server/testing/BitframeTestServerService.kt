package bitframe.server.testing

import bitframe.authentication.server.signin.SignInService
import bitframe.authentication.server.spaces.SpacesService
import bitframe.authentication.server.users.UsersService
import bitframe.server.BitframeService

fun BitframeTestServerService(
    config: BitframeTestServerServiceConfig
) = object : BitframeService(config) {
    override val spaces: SpacesService = SpacesService(config)
    override val users: UsersService = UsersService(config)
    override val signIn: SignInService = SignInService(config)
}