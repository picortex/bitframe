package bitframe.authentication.client.signin

import bitframe.events.EventBus
import bitframe.service.client.config.ServiceConfig
import cache.Cache

interface SignInServiceConfig : ServiceConfig {
    val bus: EventBus
    val cache: Cache
}