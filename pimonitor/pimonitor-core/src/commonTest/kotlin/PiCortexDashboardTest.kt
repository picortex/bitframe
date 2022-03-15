import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.core.picortex.PiCortexApiCredentials
import pimonitor.core.picortex.PiCortexDashboardProvider
import pimonitor.core.picortex.PiCortexDashboardProviderConfig
import pimonitor.core.picortex.PiCortexDashboardProviderConfig.Environment.Production
import pimonitor.core.picortex.PiCortexDashboardProviderConfig.Environment.Staging
import kotlin.test.Test

class PiCortexDashboardTest {

    val provider = PiCortexDashboardProvider(
        PiCortexDashboardProviderConfig(environment = Production)
    )

    @Test
    fun should_load_picortex_dashboard() = runTest {
        val cred = PiCortexApiCredentials(
            businessId = "testBuz",
            subdomain = "b2bdemo",
            secret = "89aqiclvjktp0aa4bgfqpbppf6"
        )
        val dashboard = provider.technicalDashboardOf(cred).await()
        println(dashboard)
    }
}