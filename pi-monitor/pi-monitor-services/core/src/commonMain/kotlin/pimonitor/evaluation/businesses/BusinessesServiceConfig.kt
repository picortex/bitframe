package pimonitor.evaluation.businesses

import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import pimonitor.monitors.MonitorsService

interface BusinessesServiceConfig : ServiceConfig {
    val bus: EventBus
    val monitorsService: MonitorsService
}