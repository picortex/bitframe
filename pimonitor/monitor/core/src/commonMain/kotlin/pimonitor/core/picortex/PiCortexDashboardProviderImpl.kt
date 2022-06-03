@file:OptIn(ExperimentalTime::class)

package pimonitor.core.picortex

import datetime.Date
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.util.date.*
import kash.Currency
import kash.Money
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.datetime.*
import kotlinx.datetime.Month
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
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

internal class PiCortexDashboardProviderImpl(
    val credentials: PiCortexApiCredentials,
    val config: PiCortexServiceConfig = PiCortexServiceConfig()
) : DashboardProvider {

    private val scope get() = config.scope
    private val client get() = config.client
    private val parser get() = config.parser
    private val domain get() = config.environment.domain

    @JvmName("diffNumber")
    @JsName("diffNumber")
    fun ValueCard<Double>.diff(other: ValueCard<Double>) = numberChangeBoxOf(
        title = title,
        previous = other.value,
        current = value,
        details = details,
        priority = priority
    )

    @JvmName("diffMoney")
    @JsName("diffMoney")
    fun ValueCard<Money>.diff(other: ValueCard<Money>) = moneyChangeBoxOf(
        title = title,
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
        val moneyBoxes = moneyCards.map {
            val current = it
            val previous = other.findMoneyCardOrDefault(it.title, it.value.currency)
            current.diff(previous)
        }.toInteroperableList()

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
    override fun technicalDifferenceDashboardOf(
        /** start time in milli seconds */
        start: Date,
        /** end time in milli seconds*/
        end: Date
    ) = scope.later {

        val period: DatePeriod = end - start
        val initial = start - period
        val dashboard1 = technicalDashboardOf(start = initial, end = start)
        val dashboard2 = technicalDashboardOf(start = start, end = end)
        val board1 = dashboard1.await()
        val board2 = dashboard2.await()
        board2.diff(board1)
    }

    fun GMTDate.toDateTime() = LocalDateTime(year, Month.valueOf(month.name), dayOfMonth, hours, minutes, seconds, 0)

    fun GMTDate.toMillis() = toDateTime().toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()

    @OptIn(ExperimentalTime::class)
    override fun technicalDashboardOf(
        /** start time in milli seconds */
        start: Date,
        /** end time in milli seconds*/
        end: Date
    ) = scope.later {
        val formatter = DateFormatter("{DD}/{MM}/{YYYY}")
        val params = mapOf(
            "secret" to credentials.secret,
            "userType" to "DataConsoleUser",
            "dateFrom" to formatter.format(start),
            "dateTo" to formatter.format(end)
        )
        val url = "https://${credentials.subdomain}.$domain/api/reporting"
        val (res, reqDuration) = measureTimedValue {
            val res = client.post(url) {
                setBody(
                    TextContent(
                        text = Mapper.encodeToString(params),
                        contentType = ContentType.Application.Json
                    )
                )
            }
            res
        }
        val (dashboard, parsingDuration) = measureTimedValue {
            parser.parseTechnicalDashboard(res.bodyAsText())
        }
        val processing = res.responseTime.toMillis() - res.requestTime.toMillis()
        val networkTime = reqDuration.inWholeMilliseconds - processing
        println("Network:   ${networkTime}ms = ${networkTime / 1000}s")
        println("Processing: ${processing}ms = ${processing / 1000}s")
        println("Parsing: ${parsingDuration.inWholeMilliseconds}ms = ${parsingDuration.inWholeSeconds}s")
        dashboard
    }
}