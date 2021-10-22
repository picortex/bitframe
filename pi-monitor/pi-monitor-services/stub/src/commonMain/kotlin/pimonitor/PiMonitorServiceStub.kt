package pimonitor

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.InMemoryAuthenticationDaoProvider
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.users.UsersService
import bitframe.authentication.users.UsersServiceImpl
import bitframe.events.InMemoryEventBus
import pimonitor.authentication.signup.SignUpServiceImpl
import pimonitor.evaluation.businesses.BusinessServiceImpl
import pimonitor.monitored.MonitoredBusinessDaoInMemory
import pimonitor.monitors.MonitorDaoInMemory
import pimonitor.monitors.MonitorsServiceImpl

fun PiMonitorServiceStub(
    config: StubServiceConfig,
    provider: AuthenticationDaoProvider = InMemoryAuthenticationDaoProvider(config.toInMemoryDaoConfig()),
    usersService: UsersService = UsersServiceImpl(provider, config),
): PiMonitorService {
    val bus = InMemoryEventBus()
    val signInService = SignInServiceImpl(provider, config, bus)
    val daoConfig = config.toInMemoryDaoConfig()
    val monitorDao = MonitorDaoInMemory(config = daoConfig)
    val monitoredBusinessDao = MonitoredBusinessDaoInMemory(config = daoConfig)
    val monitorsService = MonitorsServiceImpl(signInService.session, monitorDao, config)
    return object : PiMonitorService(
        users = usersService,
        signIn = signInService,
        signUp = SignUpServiceImpl(monitorDao, usersService, bus, config),
        monitors = monitorsService,
        businesses = BusinessServiceImpl(bus, monitoredBusinessDao, monitorsService, config),
        bus = bus
    ) {}
}