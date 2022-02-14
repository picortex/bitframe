package pimonitor.server

import bitframe.core.UserEmail
import bitframe.server.BitframeService
import bitframe.service.server.GenericService
import bitframe.service.server.config.ServiceConfig
import pimonitor.service.daod.signup.SignUpService

class PiMonitorService(config: ServiceConfig) : BitframeService(config) {
    val signUp: SignUpService by lazy { SignUpService(config) }
//    val monitors: MonitorsService by lazy { MonitorsService(config) }

    //    val businesses: BusinessesService by lazy { BusinessesService(config) }
    val userEmails: GenericService<UserEmail> by lazy { GenericService(config) }
}