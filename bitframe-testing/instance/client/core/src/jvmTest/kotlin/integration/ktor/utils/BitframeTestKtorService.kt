package integration.ktor.utils

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.client.signin.SignInServiceKtor
import bitframe.authentication.client.spaces.SpacesServiceKtor
import bitframe.authentication.client.users.UsersServiceKtor
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import bitframe.api.BitframeService
import bitframe.api.BitframeServiceKtorConfig
import bitframe.authentication.client.signout.SignOutService

internal class BitframeTestKtorService(
    override val config: BitframeServiceKtorConfig
) : BitframeService {
    override val spaces: SpacesService = SpacesServiceKtor(config)
    override val users: UsersService = UsersServiceKtor(config)
    override val signIn: SignInService = SignInServiceKtor(config)
    override val signOut: SignOutService = SignOutService(config)
}