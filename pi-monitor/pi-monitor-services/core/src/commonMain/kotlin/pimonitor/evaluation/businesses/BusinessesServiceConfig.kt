package pimonitor.evaluation.businesses

import bitframe.service.config.ServiceConfig
import pimonitor.monitors.MonitorsService

interface BusinessesServiceConfig : ServiceConfig {
    val monitorsService: MonitorsService
}