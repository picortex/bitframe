package pimonitor

import bitframe.authentication.signin.SignInServiceKtor
import bitframe.authentication.users.UsersServiceKtor
import bitframe.service.config.KtorClientConfiguration
import pimonitor.authentication.signup.SignUpServiceKtor
import pimonitor.evaluation.businesses.BusinessServiceKtor
import pimonitor.monitors.MonitorsServiceKtor

fun PiMonitorServiceKtor(
    configuration: KtorClientConfiguration
): PiMonitorService {
    val signInService = SignInServiceKtor(configuration)
    return object : PiMonitorService(
        users = UsersServiceKtor(configuration),
        signIn = signInService,
        signUp = SignUpServiceKtor(configuration),
        monitors = MonitorsServiceKtor(signInService.session, configuration),
        businesses = BusinessServiceKtor((configuration))
    ) {}
}