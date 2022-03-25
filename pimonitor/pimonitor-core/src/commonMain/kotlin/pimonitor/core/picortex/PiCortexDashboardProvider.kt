package pimonitor.core.picortex

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import kash.Currency
import kash.Money
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimePeriod
import kotlinx.datetime.Instant
import kotlinx.serialization.mapper.Mapper
import later.await
import later.later
import pimonitor.core.dashboards.DashboardProvider
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.dashboards.OperationalDifferenceBoard
import presenters.cards.ValueCard
import presenters.changes.moneyChangeBoxOf
import presenters.changes.numberChangeBoxOf
import presenters.date.DateFormatter
import kotlin.js.JsName
import kotlin.jvm.JvmName
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.ExperimentalTime

class PiCortexDashboardProvider(
    val config: PiCortexDashboardProviderConfig = PiCortexDashboardProviderConfig()
) : DashboardProvider {

    private val scope get() = config.scope
    private val client get() = config.client
    private val parser get() = config.parser
    private val domain get() = config.environment.domain

    @JvmName("diffNumber")
    @JsName("diffNumber")
    fun ValueCard<Double>.diff(other: ValueCard<Double>) = numberChangeBoxOf(
        previous = other.value,
        current = value,
        details = details,
        priority = priority
    )

    @JvmName("diffMoney")
    @JsName("diffMoney")
    fun ValueCard<Money>.diff(other: ValueCard<Money>) = moneyChangeBoxOf(
        previous = other.value,
        current = value,
        details = details,
        priority = priority
    )

    fun OperationalDashboard.findMoneyCardOrDefault(title: String, currency: Currency): ValueCard<Money> = moneyCards.firstOrNull {
        it.title == title
    } ?: ValueCard(
        title = title,
        value = currency.of(0),
        details = ""
    )

    fun OperationalDashboard.findNumberCardOrDefault(title: String): ValueCard<Double> = numberCards.firstOrNull {
        it.title == title
    } ?: ValueCard(
        title = title,
        value = 0.0,
        details = ""
    )

    fun OperationalDashboard.diff(other: OperationalDashboard): OperationalDifferenceBoard {
        println("Diffs")
        val moneyBoxes = moneyCards.map {
            val current = it
            val previous = other.findMoneyCardOrDefault(it.title, it.value.currency)
            println("Diffing: $current")
            println("Diffing: $previous")
            current.diff(previous)
        }.toInteroperableList()

        println("Done diffing money")
        val numberBoxes = numberCards.map {
            it.diff(other.findNumberCardOrDefault(it.title))
        }.toInteroperableList()
        return OperationalDifferenceBoard(
            moneyCards = moneyBoxes,
            numberCards = numberBoxes,
            charts = charts
        )
    }

    @OptIn(ExperimentalTime::class)
    fun technicalDifferenceDashboardOf(
        credentials: PiCortexApiCredentials,
        /** start time in milli seconds */
        start: Double,
        /** end time in milli seconds*/
        end: Double
    ) = scope.later {
        val point1 = Instant.fromEpochMilliseconds(end.toLong())
        val point2 = Instant.fromEpochMilliseconds(start.toLong())
        val difference: Duration = (end - start).milliseconds
        val point3 = point2 - difference
        val dashboard1 = technicalDashboardOf(
            credentials,
            start = point3.toEpochMilliseconds().toDouble(),
            end = point2.toEpochMilliseconds().toDouble()
        )

        val dashboard2 = technicalDashboardOf(
            credentials,
            start = point2.toEpochMilliseconds().toDouble(),
            end = point1.toEpochMilliseconds().toDouble()
        )

        val board1 = dashboard1.await()
        val board2 = dashboard2.await()
        board2.diff(board1).also { println("Finished diffing board") }
    }

    fun technicalDashboardOf(
        credentials: PiCortexApiCredentials,
        /** start time in milli seconds */
        start: Double,
        /** end time in milli seconds*/
        end: Double
    ) = scope.later {
        val formatter = DateFormatter("{DD}/{MM}/{YYYY}")
        val params = mapOf(
            "secret" to credentials.secret,
            "userType" to "DataConsoleUser",
            "dateFrom" to formatter.format(millis = start),
            "dateTo" to formatter.format(millis = end)
        )
        val url = "https://${credentials.subdomain}.$domain/api/reporting"
        val res = client.post(url) {
            setBody(
                TextContent(
                    text = Mapper.encodeToString(params),
                    contentType = ContentType.Application.Json
                )
            )
        }
        parser.parseTechnicalDashboard(res.bodyAsText())
    }
}