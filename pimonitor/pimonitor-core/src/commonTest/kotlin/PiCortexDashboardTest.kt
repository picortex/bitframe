import datetime.Date
import io.ktor.client.*
import io.ktor.client.plugins.*
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import later.await
import pimonitor.core.picortex.PiCortexApiCredentials
import pimonitor.core.picortex.PiCortexDashboardProvider
import pimonitor.core.picortex.PiCortexDashboardProviderConfig
import pimonitor.core.picortex.PiCortexDashboardProviderConfig.Environment.Production
import pimonitor.core.picortex.PiCortexDashboardProviderConfig.Environment.Staging
import kotlin.test.Test

class PiCortexDashboardTest {

    val environment = Production
    val provider = PiCortexDashboardProvider(
        PiCortexDashboardProviderConfig(
            environment = environment,
            client = HttpClient {
                install(HttpTimeout) {
                    requestTimeoutMillis = 60000L
                }
            }
        )
    )

    val stagingCredentials = PiCortexApiCredentials(
        businessId = "testBuz",
        subdomain = "b2b",
        secret = "f225ela32hovtvo4s1bj466j1p"
    )

    val productionCredentialsPicortex = PiCortexApiCredentials(
        businessId = "testBuz",
        subdomain = "b2bDemo",
        secret = "89aqiclvjktp0aa4bgfqpbppf6"
    )

    val productionCredentialsZL = PiCortexApiCredentials(
        businessId = "testBuz",
        subdomain = "ziyahlanjwa",
        secret = "fbjkfsk4vk34n05nrtr4g3r156"
    )

    val productionCredentialsLudada = PiCortexApiCredentials(
        businessId = "testBuz",
        subdomain = "ludada",
        secret = "a8lch29mut8tp4r5kvm2vtgakc"
    )

    val credentials
        get() = when (environment) {
            Staging -> stagingCredentials
            Production -> productionCredentialsZL
        }

    @Test
    fun should_load_picortex_dashboard() = runTest {
        val now = Clock.System.now()
        val today = Date.today()
        val period = DatePeriod(days = 30)
        val oneLastMonth = today - period
        val twoLastMonths = oneLastMonth - period
        val dashboard1 = provider.technicalDashboardOf(
            credentials,
            start = oneLastMonth,
            end = today
        )
        val dashboard2 = provider.technicalDashboardOf(
            credentials,
            start = twoLastMonths,
            end = oneLastMonth
        )

        val board1 = dashboard1.await()
        println(board1)
        println(dashboard2.await())

        println(board1.cards)
    }

    @Test
    fun should_load_picortex_difference_dashboard() = runTest {
        val today = Date.today()
        val oneLastMonth = today - DatePeriod(days = 30)
        val dashboard = provider.technicalDifferenceDashboardOf(
            credentials,
            start = oneLastMonth,
            end = today
        )
        dashboard.await()
    }
}