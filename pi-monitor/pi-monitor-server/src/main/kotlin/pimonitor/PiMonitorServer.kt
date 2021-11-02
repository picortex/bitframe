package pimonitor

import bitframe.Application
import bitframe.daos.config.InMemoryDaoConfig
import bitframe.events.InMemoryEventBus
import bitframe.server.modules.Module
import bitframe.server.modules.authentication.AuthenticationModuleImpl
import bitframe.server.modules.authentication.AuthenticationService
import bitframe.service.config.ServiceConfig
import cache.Cache
import cache.MockCache
import pimonitor.authentication.signup.SignUpController
import pimonitor.authentication.signup.SignUpModule
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor
import pimonitor.monitors.MonitorDaoInMemory
import java.io.File

fun PiMonitorServer(
    client: File,
    cache: Cache,
    authService: AuthenticationService,
): Application {
    val bus = InMemoryEventBus()
    return Application(
        client,
        AuthenticationModuleImpl(bus, cache, authService),
        listOf(
            SignUpModule(
                controller = SignUpController(
                    dao = MonitorDaoInMemory(config = InMemoryDaoConfig(0)),
                    config = ServiceConfig(""),
                    service = authService.users,
                    bus = bus
                )
            ),
            Module<IndividualMonitor>(),
            Module<CooperateMonitor>("monitor-businesses"),
            Module<MonitoredBusiness>(),
        )
    )
}