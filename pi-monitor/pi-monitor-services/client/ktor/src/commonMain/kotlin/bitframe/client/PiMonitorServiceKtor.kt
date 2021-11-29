package bitframe.client

import bitframe.authentication.client.signin.SignInServiceKtor
import bitframe.authentication.client.spaces.SpacesServiceKtor
import bitframe.authentication.client.users.UsersServiceKtor
import bitframe.client.authentication.signup.SignUpServiceKtor
import bitframe.client.evaluation.businesses.BusinessesServiceKtor
import bitframe.client.monitors.MonitorsServiceKtor

fun PiMonitorServiceKtor(
    config: PiMonitorServiceKtorConfig,
) = object : PiMonitorService {
    override val spaces by lazy { SpacesServiceKtor(config) }
    override val users by lazy { UsersServiceKtor(config) }
    override val signIn by lazy { SignInServiceKtor(config) }
    override val signUp by lazy { SignUpServiceKtor(config) }
    override val monitors by lazy { MonitorsServiceKtor(config) }
    override val businesses by lazy { BusinessesServiceKtor(config) }
}