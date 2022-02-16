package pimonitor.server

import bitframe.server.BitframeService
import bitframe.server.ServiceConfig
import pimonitor.core.signup.SignUpDaodService
import pimonitor.core.signup.SignUpService

class PiMonitorService(config: ServiceConfig) : BitframeService(config) {
    val signup: SignUpService by lazy { SignUpDaodService(config) }
//    val monitors: MonitorsService by lazy { MonitorsService(config) }

    //    val businesses: BusinessesService by lazy { BusinessesService(config) }
//    val userEmails: GenericService<UserEmail> by lazy { GenericService(config) }
}