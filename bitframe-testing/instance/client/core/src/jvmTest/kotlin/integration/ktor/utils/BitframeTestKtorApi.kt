package integration.ktor.utils

import bitframe.authentication.client.signin.SignInService
import bitframe.client.signin.SignInServiceKtor
import bitframe.client.spaces.SpacesServiceKtor
import bitframe.client.users.UsersServiceKtor
import bitframe.core.spaces.SpacesService
import bitframe.core.users.UsersService
import bitframe.client.BitframeApi
import bitframe.client.BitframeApiKtorConfig
import bitframe.authentication.client.signout.SignOutService

internal class BitframeTestKtorApi(
    override val config: BitframeApiKtorConfig
) : BitframeApi {
    override val spaces: SpacesService = SpacesServiceKtor(config)
    override val users: UsersService = UsersServiceKtor(config)
    override val signIn: SignInService = SignInServiceKtor(config)
    override val signOut: SignOutService = SignOutService(config)
}