package pimonitor.client

import bitframe.client.BitframeApiKtorConfig
import bitframe.client.SessionAware
import bitframe.client.SessionAwareImpl
import bitframe.client.signin.SignInServiceKtor
import bitframe.client.signout.SignOutService
import bitframe.client.spaces.SpacesServiceKtor
import bitframe.client.users.UsersServiceKtor
import pimonitor.client.signup.SignUpServiceKtor

class PiMonitorApiKtor(
    override val config: BitframeApiKtorConfig,
) : PiMonitorApi {
    override val session by lazy { SessionAwareImpl(this) }
    override val spaces by lazy { SpacesServiceKtor(config) }
    override val users by lazy { UsersServiceKtor(config) }
    override val signIn by lazy { SignInServiceKtor(config) }
    override val signUp by lazy { SignUpServiceKtor(config) }
    override val signOut by lazy { SignOutService(config) }
//    override val monitors by lazy { MonitorsServiceKtor(config) }
//    override val businesses by lazy { BusinessesServiceKtor(config) }
//    override val portfolio by lazy { PortfolioService(businesses) }
}