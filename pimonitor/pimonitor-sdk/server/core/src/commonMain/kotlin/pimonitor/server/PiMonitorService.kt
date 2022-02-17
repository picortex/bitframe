package pimonitor.server

import bitframe.server.BitframeService
import bitframe.server.ServiceConfig
import pimonitor.core.businesses.BusinessesDaodService
import pimonitor.core.signup.SignUpDaodService
import pimonitor.core.signup.SignUpService

class PiMonitorService(config: ServiceConfig) : BitframeService(config) {
    val signup by lazy { SignUpDaodService(config) }
    val businesses by lazy { BusinessesDaodService(config) }
    
//    val monitors: MonitorsService by lazy { MonitorsService(config) }
//    val userEmails: GenericService<UserEmail> by lazy { GenericService(config) }
}