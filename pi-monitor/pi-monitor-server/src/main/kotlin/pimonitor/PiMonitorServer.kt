package pimonitor

import bitframe.Application
import bitframe.daos.config.InMemoryDaoConfig
import bitframe.server.modules.Module
import bitframe.server.modules.authentication.AuthenticationService
import bitframe.server.modules.authentication.AuthenticationModuleImpl
import bitframe.service.config.ServiceConfig
import pimonitor.authentication.signup.SignUpController
import pimonitor.authentication.signup.SignUpModule
import pimonitor.monitors.MonitorDaoInMemory
import java.io.File

fun PiMonitorServer(
    client: File,
    authService: AuthenticationService,
) = Application(
    client,
    AuthenticationModuleImpl(authService),
    listOf(
        SignUpModule(
            controller = SignUpController(
                dao = MonitorDaoInMemory(config = InMemoryDaoConfig(0)),
                config = ServiceConfig(""),
                service = authService.users
            )
        ),
        Module<Monitor>(),
        Module<Monitor.Business>("monitor-businesses"),
        Module<Monitored>(),
    )
)