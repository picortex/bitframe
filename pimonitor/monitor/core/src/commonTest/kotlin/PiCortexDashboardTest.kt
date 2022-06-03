@file:OptIn(ExperimentalTime::class)

import datetime.Date
import io.ktor.client.*
import io.ktor.client.plugins.*
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import later.await
import pimonitor.core.picortex.PiCortexApiCredentials
import pimonitor.core.picortex.PiCortexService
import pimonitor.core.picortex.PiCortexServiceConfig
import pimonitor.core.picortex.PiCortexServiceConfig.Environment.Production
import pimonitor.core.picortex.PiCortexServiceConfig.Environment.Staging
import pimonitor.core.picortex.PiCortexServiceMock
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.time.Duration
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

class PiCortexDashboardTest {

    val environment = Production
    val service: PiCortexService = PiCortexService(
        PiCortexServiceConfig(
            environment = environment,
            client = HttpClient {
                install(HttpTimeout) {
                    requestTimeoutMillis = 60000L
                }
            }
        )
    )

//    val service: PiCortexService = PiCortexServiceMock()

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

    val provider get() = service.offeredTo(credentials)

    @Test
    fun should_load_picortex_dashboard() = runTest {
        val now = Clock.System.now()
        val today = Date.today()
        val period = DatePeriod(days = 30)
        val oneLastMonth = today - period
        val twoLastMonths = oneLastMonth - period
        val dashboard1 = provider.technicalDashboardOf(
            start = oneLastMonth,
            end = today
        )
        val dashboard2 = provider.technicalDashboardOf(
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
            start = oneLastMonth,
            end = today
        )
        dashboard.await()
    }

    @OptIn(ExperimentalTime::class)
    @Test
//    @Ignore
    fun should_get_response_time() = runTest {
        val credentials = PiCortexApiCredentials(
            "Ziyahlanjwa Trading (Pty) Ltd",
            "ziyahlanjwa",
            "fbjkfsk4vk34n05nrtr4g3r156"
        )
        val provider = service.offeredTo(credentials)
        val today = Date.today()
        val oneLastMonth = today - DatePeriod(days = 30)

        val durations = mutableListOf<Duration>()
        repeat(10) {
            val (board, duration) = measureTimedValue {
                val dashboard = provider.technicalDifferenceDashboardOf(
                    start = oneLastMonth,
                    end = today
                )
                dashboard.await()
            }
            durations.add(duration)

            val avMs = durations.map { d -> d.inWholeMilliseconds }.average()
            val avSs = durations.map { d -> d.inWholeSeconds }.average()
            // previous: 18s
            println("T${it + 1}: ${duration.inWholeMilliseconds}ms = ${duration.inWholeSeconds}s")
            println("A${it + 1}: ${avMs}ms = ${avSs}s")
        }
    }
}