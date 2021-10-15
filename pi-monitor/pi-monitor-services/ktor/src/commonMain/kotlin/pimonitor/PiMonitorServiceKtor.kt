package pimonitor

import bitframe.authentication.signin.SignInServiceKtor
import bitframe.authentication.users.UsersServiceKtor
import bitframe.events.InMemoryEventBus
import bitframe.service.config.KtorClientConfiguration
import pimonitor.authentication.signup.SignUpServiceKtor
import pimonitor.evaluation.businesses.BusinessServiceKtor
import pimonitor.monitors.MonitorsServiceKtor

fun PiMonitorServiceKtor(
    configuration: KtorClientConfiguration
): PiMonitorService {
    val bus = InMemoryEventBus()
    val signInService = SignInServiceKtor(configuration, bus)
    return object : PiMonitorService(
        users = UsersServiceKtor(configuration),
        signIn = signInService,
        signUp = SignUpServiceKtor(bus, configuration),
        monitors = MonitorsServiceKtor(signInService.session, configuration),
        businesses = BusinessServiceKtor((configuration)),
        bus = bus
    ) {}
}