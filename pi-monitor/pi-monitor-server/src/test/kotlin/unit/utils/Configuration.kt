package unit.utils

import bitframe.ApplicationUnderTest
import bitframe.server.InMemoryDaoProvider
import bitframe.server.modules.authentication.DefaultAuthenticationModule
import bitframe.server.modules.authentication.DefaultAuthenticationService
import pimonitor.PiMonitorServer
import java.io.File

@JvmField
val DAO_PROVIDER_UNDER_TEST = InMemoryDaoProvider()

val AUTH_SERVICE = DefaultAuthenticationService(DAO_PROVIDER_UNDER_TEST)

val SERVER = ApplicationUnderTest(
    PiMonitorServer(
        client = File("."),
        AUTH_SERVICE
    )
)