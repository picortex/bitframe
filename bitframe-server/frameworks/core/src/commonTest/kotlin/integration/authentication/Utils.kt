package integration.authentication

import events.EventBus
import events.InMemoryEventBus
import bitframe.service.config.ServiceConfig
import cache.Cache
import cache.MockCache

internal val SERVICE_CONFIG = ServiceConfig()

internal val BUS: EventBus = InMemoryEventBus()

internal val CACHE: Cache = MockCache()