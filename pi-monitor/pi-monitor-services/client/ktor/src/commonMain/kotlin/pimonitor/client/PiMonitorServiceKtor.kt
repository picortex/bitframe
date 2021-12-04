package pimonitor.client

import bitframe.authentication.client.signin.SignInServiceKtor
import bitframe.authentication.client.spaces.SpacesServiceKtor
import bitframe.authentication.client.users.UsersServiceKtor
import pimonitor.client.authentication.signup.SignUpServiceKtor
import pimonitor.client.evaluation.businesses.BusinessesServiceKtor
import pimonitor.client.monitors.MonitorsServiceKtor
import pimonitor.client.PiMonitorService

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