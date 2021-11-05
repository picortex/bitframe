package pimonitor.client

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.client.signin.SignInServiceKtor
import bitframe.authentication.client.spaces.SpacesServiceKtor
import bitframe.authentication.client.users.UsersServiceKtor
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersService
import pimonitor.authentication.signup.SignUpService
import pimonitor.client.authentication.signup.SignUpServiceKtor
import pimonitor.client.evaluation.businesses.BusinessesServiceKtor
import pimonitor.client.evaluation.businesses.BusinessesService
import pimonitor.client.monitors.MonitorsService
import pimonitor.client.monitors.MonitorsServiceKtor

fun PiMonitorServiceKtor(
    config: PiMonitorServiceKtorConfig,
): PiMonitorService {
    val signInService = SignInServiceKtor(config)
    val monitorsService = MonitorsServiceKtor(config.with(signInService))
    return object : PiMonitorService(config) {
        override val spaces: SpacesService = SpacesServiceKtor(config)
        override val users: UsersService = UsersServiceKtor(config)
        override val signIn: SignInService = signInService
        override val signUp: SignUpService = SignUpServiceKtor(config)
        override val monitors: MonitorsService = monitorsService
        override val businesses: BusinessesService = BusinessesServiceKtor(config.with(monitorsService))
    }
}