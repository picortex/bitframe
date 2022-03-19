import kash.Currency
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.core.picortex.PiCortexApiCredentials
import pimonitor.core.picortex.PiCortexDashboardProvider
import pimonitor.core.picortex.PiCortexDashboardProviderConfig
import pimonitor.core.picortex.PiCortexDashboardProviderConfig.Environment.Production
import pimonitor.core.picortex.PiCortexDashboardProviderConfig.Environment.Staging
import kotlin.test.Ignore
import kotlin.test.Test

class PiCortexDashboardTest {

    val environment = Staging
    val provider = PiCortexDashboardProvider(
        PiCortexDashboardProviderConfig(environment = Staging)
    )

    val stagingCredentials = PiCortexApiCredentials(
        businessId = "testBuz",
        subdomain = "b2b",
        secret = "f225ela32hovtvo4s1bj466j1p"
    )

    val productionCredentials = PiCortexApiCredentials(
        businessId = "testBuz",
        subdomain = "b2bDemo",
        secret = "89aqiclvjktp0aa4bgfqpbppf6"
    )

    val credentials
        get() = when (environment) {
            Staging -> stagingCredentials
            Production -> productionCredentials
        }

    @Test
    fun should_load_picortex_dashboard() = runTest {
        val dashboard = provider.technicalDashboardOf(credentials).await()
        println(dashboard)

        Currency
    }
}