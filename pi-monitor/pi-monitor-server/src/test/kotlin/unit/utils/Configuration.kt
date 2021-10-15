package unit.utils

import bitframe.ApplicationUnderTest
import bitframe.events.InMemoryEventBus
import bitframe.server.InMemoryDaoProvider
import bitframe.server.modules.authentication.AuthenticationServiceImpl
import bitframe.service.config.ServiceConfig
import pimonitor.PiMonitorServer
import java.io.File

@JvmField
val DAO_PROVIDER_UNDER_TEST = InMemoryDaoProvider()

val SERVICE_CONFIG = ServiceConfig(
    "server-app"
)

internal val BUS = InMemoryEventBus()

val AUTH_SERVICE = AuthenticationServiceImpl(DAO_PROVIDER_UNDER_TEST, SERVICE_CONFIG, BUS)

val SERVER = ApplicationUnderTest(
    PiMonitorServer(
        client = File("."),
        AUTH_SERVICE
    )
)