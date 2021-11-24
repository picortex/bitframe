package unit.utils

import bitframe.ApplicationUnderTest
import bitframe.events.InMemoryEventBus
import bitframe.server.InMemoryDaoProvider
import bitframe.server.modules.authentication.services.AuthenticationServiceImpl
import bitframe.service.config.ServiceConfig
import cache.Cache
import cache.MockCache
import bitframe.PiMonitorServer
import java.io.File

@JvmField
val DAO_PROVIDER_UNDER_TEST = InMemoryDaoProvider()

val SERVICE_CONFIG = ServiceConfig(
    "server-app"
)

internal val BUS = InMemoryEventBus()

internal val CACHE: Cache = MockCache()

val AUTH_SERVICE = AuthenticationServiceImpl(DAO_PROVIDER_UNDER_TEST, SERVICE_CONFIG, CACHE, BUS)

val SERVER = ApplicationUnderTest(
    PiMonitorServer(
        client = File("."),
        CACHE,
        AUTH_SERVICE
    )
)