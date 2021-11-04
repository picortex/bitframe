package pimonitor.server

import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig

interface PiMonitorServiceConfig : ServiceConfig {
    val bus: EventBus
}