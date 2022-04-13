import datetime.Date
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