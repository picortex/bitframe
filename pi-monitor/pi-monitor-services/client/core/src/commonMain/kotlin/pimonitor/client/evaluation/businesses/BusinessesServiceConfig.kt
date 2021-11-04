package pimonitor.client.evaluation.businesses

import bitframe.service.client.config.ServiceConfig
import pimonitor.client.monitors.MonitorsService

interface BusinessesServiceConfig : ServiceConfig {
    val monitorsService: MonitorsService
}