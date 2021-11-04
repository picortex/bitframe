package pimonitor

import bitframe.Application
import bitframe.ApplicationConfig
import bitframe.server.modules.Module
import bitframe.server.with
import pimonitor.authentication.signup.SignUpController
import pimonitor.authentication.signup.SignUpModule
import pimonitor.data.PiMonitorDAOProvider
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor

fun PiMonitorServer(config: PiMonitorApplicationConfig): Application<PiMonitorDAOProvider> {
    val cfg = config.with(
        SignUpModule(
            controller = SignUpController(config)
        ),
        Module<IndividualMonitor>(),
        Module<CooperateMonitor>("monitor-businesses"),
        Module<MonitoredBusiness>(),
    )
    return Application(cfg)
}