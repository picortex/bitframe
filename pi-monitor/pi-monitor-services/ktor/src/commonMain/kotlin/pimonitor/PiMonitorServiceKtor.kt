package pimonitor

import bitframe.authentication.signin.SignInServiceKtor
import bitframe.authentication.spaces.SpacesServiceKtor
import bitframe.authentication.users.UsersServiceKtor
import bitframe.events.InMemoryEventBus
import bitframe.service.config.KtorClientConfiguration
import cache.Cache
import pimonitor.authentication.signup.SignUpServiceKtor
import pimonitor.evaluation.businesses.BusinessServiceKtor
import pimonitor.monitors.MonitorsServiceKtor

fun PiMonitorServiceKtor(
    config: KtorClientConfiguration,
    cache: Cache
): PiMonitorService {
    val bus = InMemoryEventBus()
    val signInService = SignInServiceKtor(config, cache, bus)
    val monitorService = MonitorsServiceKtor(signInService.session, config)
    return object : PiMonitorService(
        spaces = SpacesServiceKtor(config),
        users = UsersServiceKtor(config),
        signIn = signInService,
        signUp = SignUpServiceKtor(bus, config),
        monitors = monitorService,
        businesses = BusinessServiceKtor(bus, monitorService, config),
        cache = cache,
        bus = bus
    ) {}
}