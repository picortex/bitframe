package pimonitor

import bitframe.authentication.AuthenticationDaoProvider
import bitframe.authentication.InMemoryAuthenticationDaoProvider
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.users.UsersService
import bitframe.authentication.users.UsersServiceImpl
import bitframe.daos.config.InMemoryDaoConfig
import bitframe.service.config.ServiceConfig
import pimonitor.authentication.signup.SignUpServiceImpl
import pimonitor.evaluation.businesses.BusinessServiceImpl
import pimonitor.monitors.MonitorDaoInMemory

class PiMonitorServiceStub(
    config: StubServiceConfig,
    provider: AuthenticationDaoProvider = InMemoryAuthenticationDaoProvider(config.toInMemoryDaoConfig()),
    usersService: UsersService = UsersServiceImpl(provider, config),
) : PiMonitorService(
    users = usersService,
    signIn = SignInServiceImpl(provider, config),
    signUp = SignUpServiceImpl(MonitorDaoInMemory(config = config.toInMemoryDaoConfig()), usersService, config),
    businesses = BusinessServiceImpl()
)