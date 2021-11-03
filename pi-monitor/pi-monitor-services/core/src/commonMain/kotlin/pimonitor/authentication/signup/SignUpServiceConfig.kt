package pimonitor.authentication.signup

import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig

interface SignUpServiceConfig : ServiceConfig {
    val bus: EventBus
}