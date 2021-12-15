package integration

import kotlinx.coroutines.runTest
import later.await
import pimonitor.integrations.PiCortexDashboardProvider
import pimonitor.integrations.PiCortexUserCredentials
import kotlin.test.Test

class PiCortexDashboardProviderTest {
    val provider = PiCortexDashboardProvider()

    val credentials = PiCortexUserCredentials(
        subdomain = "b2bdemo", // https://b2b.picortex.co/
        secret = "89aqiclvjktp0aa4bgfqpbppf6"
    )

    @Test
    fun should_fetch_data_from_picortex() = runTest {
        val dashboard = provider.technicalDashboardOf(credentials).await()
        println(dashboard)
    }
}