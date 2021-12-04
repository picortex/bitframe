package unit.utils

import bitframe.ApplicationUnderTest
import pimonitor.PiMonitorApplicationConfig
import bitframe.events.InMemoryEventBus
import bitframe.server.InMemoryDaoProvider
import bitframe.service.config.ServiceConfig
import cache.Cache
import cache.MockCache
import pimonitor.PiMonitorServer
import java.io.File

@JvmField
val DAO_PROVIDER_UNDER_TEST = InMemoryDaoProvider()

internal val BUS = InMemoryEventBus()

internal val CACHE: Cache = MockCache()

val SERVICE_CONFIG = ServiceConfig(
    BUS
)

//val SERVER = ApplicationUnderTest(
//    PiMonitorServer(
//        config = PiMonitorApplicationConfig(
//            client = File(".")
//        )
//    )
//)