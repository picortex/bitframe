package pimonitor.client

import bitframe.authentication.client.signin.SignInServiceKtor
import bitframe.authentication.client.spaces.SpacesServiceKtor
import bitframe.authentication.client.users.UsersServiceKtor
import pimonitor.client.authentication.signup.SignUpServiceKtor
import pimonitor.client.evaluation.businesses.BusinessesServiceKtor
import pimonitor.client.monitors.MonitorsServiceKtor

fun PiMonitorServiceKtor(
    config: PiMonitorServiceKtorConfig,
) = object : PiMonitorService(config) {
    override val spaces = SpacesServiceKtor(config)
    override val users = UsersServiceKtor(config)
    override val signIn = SignInServiceKtor(config)
    override val signUp = SignUpServiceKtor(config)
    override val monitors = MonitorsServiceKtor(config)
    override val businesses = BusinessesServiceKtor(config)
}