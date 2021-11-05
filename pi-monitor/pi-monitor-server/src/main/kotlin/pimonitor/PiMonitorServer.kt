package pimonitor

import bitframe.Application
import bitframe.server.modules.Module
import bitframe.server.with
import pimonitor.authentication.signup.SignUpController
import pimonitor.authentication.signup.SignUpModule
import pimonitor.evaluation.BusinessController
import pimonitor.evaluation.BusinessesModule
import pimonitor.monitored.MonitoredBusiness
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor
import pimonitor.monitors.MonitorModule
import pimonitor.monitors.MonitorsController
import pimonitor.server.PiMonitorService
import pimonitor.server.populateTestEntities

fun PiMonitorServer(config: PiMonitorApplicationConfig): Application<PiMonitorService> {
    val cfg = config.with(
        SignUpModule(SignUpController(config.service.signUp)),
        MonitorModule(MonitorsController(config.service.monitors)),
        BusinessesModule(BusinessController(config.service)),
    )
    return object : Application<PiMonitorService>(cfg) {
        override suspend fun onStart(service: PiMonitorService) {
            service.populateTestEntities()
        }
    }
}