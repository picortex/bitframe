package unit.utils

import bitframe.ApplicationUnderTest
import bitframe.server.InMemoryDaoProvider
import bitframe.server.modules.authentication.AuthenticationServiceImpl
import pimonitor.PiMonitorServer
import java.io.File

@JvmField
val DAO_PROVIDER_UNDER_TEST = InMemoryDaoProvider()

val AUTH_SERVICE = AuthenticationServiceImpl(DAO_PROVIDER_UNDER_TEST)

val SERVER = ApplicationUnderTest(
    PiMonitorServer(
        client = File("."),
        AUTH_SERVICE
    )
)