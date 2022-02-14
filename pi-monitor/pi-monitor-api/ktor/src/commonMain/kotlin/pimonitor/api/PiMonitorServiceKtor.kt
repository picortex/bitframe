package pimonitor.api

import bitframe.client.signin.SignInServiceKtor
import bitframe.authentication.client.signout.SignOutService
import bitframe.client.spaces.SpacesServiceKtor
import bitframe.client.users.UsersServiceKtor
import pimonitor.client.authentication.signup.SignUpServiceKtor

fun PiMonitorServiceKtor(
    config: PiMonitorApiKtorConfig,
) = object : PiMonitorApi {
    override val config: PiMonitorApiKtorConfig = config
    override val spaces by lazy { SpacesServiceKtor(config) }
    override val users by lazy { UsersServiceKtor(config) }
    override val signIn by lazy { SignInServiceKtor(config) }
    override val signUp by lazy { SignUpServiceKtor(config) }
    override val signOut by lazy { SignOutService(config) }
//    override val monitors by lazy { MonitorsServiceKtor(config) }
//    override val businesses by lazy { BusinessesServiceKtor(config) }
//    override val portfolio by lazy { PortfolioService(businesses) }
}