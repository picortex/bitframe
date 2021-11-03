package pimonitor

import bitframe.authentication.client.signin.SignInServiceKtor
import bitframe.authentication.client.signin.SignInServiceKtorConfig
import bitframe.authentication.client.spaces.SpacesServiceKtor
import bitframe.authentication.client.users.UsersServiceKtor
import bitframe.events.InMemoryEventBus
import bitframe.service.client.config.KtorClientConfiguration
import cache.Cache
import pimonitor.authentication.signup.SignUpServiceKtor
import pimonitor.evaluation.businesses.BusinessServiceKtor
import pimonitor.monitors.MonitorsServiceKtor

fun PiMonitorServiceKtor(
    config: KtorClientConfiguration,
    cache: Cache
): PiMonitorService {
    val bus = InMemoryEventBus()
    val signInService = SignInServiceKtor(
        config = SignInServiceKtorConfig(
            appId = "", scope = config.scope, cache = cache, bus = bus, http = config.http, url = config.url
        )
    )
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