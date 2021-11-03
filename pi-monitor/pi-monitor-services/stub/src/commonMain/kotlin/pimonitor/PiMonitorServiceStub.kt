package pimonitor

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.InMemoryAuthenticationDaoProvider
import bitframe.authentication.client.signin.SignInServiceMock
import bitframe.authentication.client.signin.SignInServiceMockConfig
import bitframe.authentication.spaces.SpacesServiceImpl
import bitframe.authentication.users.UsersService
import bitframe.authentication.users.UsersServiceImpl
import bitframe.events.InMemoryEventBus
import cache.Cache
import cache.MockCache
import cache.MockCacheConfig
import pimonitor.evaluation.businesses.BusinessServiceImpl
import pimonitor.monitored.MonitoredBusinessDaoInMemory
import pimonitor.monitors.MonitorDaoInMemory
import pimonitor.monitors.MonitorsServiceImpl

fun PiMonitorServiceStub(
    config: StubServiceConfig,
    cache: Cache = MockCache(config = MockCacheConfig()),
    provider: AuthenticationDaoProvider = InMemoryAuthenticationDaoProvider(config.toInMemoryDaoConfig()),
    usersService: UsersService = UsersServiceImpl(provider, config),
): PiMonitorService {
    val bus = InMemoryEventBus()
    val signInService = SignInServiceMock(
        config = SignInServiceMockConfig(usersDao = provider.users, scope = config.scope, cache = cache, bus = bus)
    )
    val daoConfig = config.toInMemoryDaoConfig()
    val monitorDao = MonitorDaoInMemory(config = daoConfig)
    val monitoredBusinessDao = MonitoredBusinessDaoInMemory(config = daoConfig)
    val monitorsService = MonitorsServiceImpl(signInService.session, monitorDao, config)
    val service = object : PiMonitorService(
        spaces = SpacesServiceImpl(config, provider.spaces, provider.users),
        users = usersService,
        signIn = signInService,
        signUp = SignUpServiceImpl(monitorDao, usersService, bus, config),
        monitors = monitorsService,
        businesses = BusinessServiceImpl(bus, monitoredBusinessDao, monitorsService, config),
        cache = cache,
        bus = bus
    ) {}
    service.populateTestEntities()
    return service
}