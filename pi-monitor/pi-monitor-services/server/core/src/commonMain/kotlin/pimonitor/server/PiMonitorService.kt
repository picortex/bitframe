@file:JsExport

package pimonitor.server

import bitframe.server.BitframeService
import bitframe.service.server.config.ServiceConfig
import pimonitor.server.authentication.signup.SignUpService
import pimonitor.server.businesses.BusinessesService
import pimonitor.server.monitors.MonitorsService
import kotlin.js.JsExport

class PiMonitorService(val config: ServiceConfig) : BitframeService(config) {
    val signUp: SignUpService = SignUpService(config)
    val monitors: MonitorsService = MonitorsService(config)
    val businesses: BusinessesService = BusinessesService(config)
}