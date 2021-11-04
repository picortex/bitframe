package pimonitor

import bitframe.Application
import bitframe.server.modules.Module
import bitframe.server.with
import pimonitor.authentication.signup.SignUpController
import pimonitor.authentication.signup.SignUpModule
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor
import pimonitor.server.PiMonitorService

fun PiMonitorServer(config: PiMonitorApplicationConfig): Application<PiMonitorService> {
    val cfg = config.with(
        SignUpModule(
            controller = SignUpController(config.service.signUp)
        ),
        Module<IndividualMonitor>(),
        Module<CooperateMonitor>("monitor-businesses"),
        Module<MonitoredBusiness>(),
    )
    return Application(cfg)
}