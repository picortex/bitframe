package integration.authentication

import bitframe.events.EventBus
import bitframe.events.InMemoryEventBus
import bitframe.server.BitframeApplicationConfig
import bitframe.server.modules.authentication.controllers.AuthenticationControllerImpl
import bitframe.service.config.ServiceConfig
import cache.Cache
import cache.MockCache

internal val SERVICE_CONFIG = ServiceConfig()

internal val BUS: EventBus = InMemoryEventBus()

internal val CACHE: Cache = MockCache()