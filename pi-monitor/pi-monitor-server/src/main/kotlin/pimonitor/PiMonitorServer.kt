package pimonitor

import bitframe.Application
import bitframe.server.modules.Module
import bitframe.server.modules.authentication.AuthenticationService
import bitframe.server.modules.authentication.AuthenticationModuleImpl
import pimonitor.authentication.signup.SignUpController
import pimonitor.authentication.signup.SignUpModule
import java.io.File

fun PiMonitorServer(
    client: File,
    authService: AuthenticationService,
) = Application(
    client,
    AuthenticationModuleImpl(authService),
    listOf(
        SignUpModule(controller = SignUpController(authService.users)),
        Module<Monitor>(),
        Module<Monitor.Business>("monitor-businesses"),
        Module<Monitored>(),
    )
)