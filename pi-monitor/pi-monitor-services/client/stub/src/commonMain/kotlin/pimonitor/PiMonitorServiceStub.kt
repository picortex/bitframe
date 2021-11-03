package pimonitor

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.client.signin.SignInServiceMock
import bitframe.authentication.spaces.SpacesService
import bitframe.authentication.users.UsersDaoInMemory
import bitframe.authentication.users.UsersService
import pimonitor.authentication.signup.SignUpService
import pimonitor.client.PiMonitorService
import pimonitor.client.monitors.MonitorsService
import pimonitor.evaluation.businesses.BusinessesService
import pimonitor.monitored.MonitoredBusinessDaoInMemory
import pimonitor.monitors.MonitorDaoInMemory

fun PiMonitorServiceStub(
    config: PiMonitorServiceStubConfig,
): PiMonitorService {
    val usersDao = UsersDaoInMemory()
    val signInService = SignInServiceMock(config.with(usersDao))
    val daoConfig = config.toInMemoryDaoConfig()
    val monitorDao = MonitorDaoInMemory(config = daoConfig)
    val monitoredBusinessDao = MonitoredBusinessDaoInMemory(config = daoConfig)
//    return object : PiMonitorService(config) {
//        override val spaces: SpacesService = SpacesService()
//        override val users: UsersService = UsersServiceImpl()
//        override val signIn: SignInService = signInService
//        override val signUp: SignUpService = SignUpService()
//        override val monitors: MonitorsService = MonitorsService()
//        override val businesses: BusinessesService = BusinessesService()
//    }
    TODO()
}