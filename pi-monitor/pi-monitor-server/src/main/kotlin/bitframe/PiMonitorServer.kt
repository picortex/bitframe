package bitframe

import bitframe.server.with
import bitframe.authentication.signup.SignUpController
import bitframe.authentication.signup.SignUpModule
import bitframe.evaluation.BusinessController
import bitframe.evaluation.BusinessesModule
import bitframe.monitors.MonitorModule
import bitframe.monitors.MonitorsController
import bitframe.server.PiMonitorService
import bitframe.server.populateTestEntities

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