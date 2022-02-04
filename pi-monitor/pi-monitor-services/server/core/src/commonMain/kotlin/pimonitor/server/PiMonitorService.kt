package pimonitor.server

import bitframe.server.BitframeService
import bitframe.service.server.config.ServiceConfig
import pimonitor.server.authentication.signup.SignUpService
import pimonitor.server.businesses.BusinessesService
import pimonitor.server.monitors.MonitorsService

class PiMonitorService(config: ServiceConfig) : BitframeService(config) {
    val signUp: SignUpService by lazy { SignUpService(config) }
    val monitors: MonitorsService by lazy { MonitorsService(config) }
    val businesses: BusinessesService by lazy { BusinessesService(config) }
}