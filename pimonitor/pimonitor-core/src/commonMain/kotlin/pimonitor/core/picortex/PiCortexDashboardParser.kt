package pimonitor.core.picortex

import kash.Currency
import kash.Money
import kotlinx.collections.interoperable.listOf
import kotlinx.collections.interoperable.toInteroperableList
import kotlinx.serialization.json.Json
import kotlinx.serialization.mapper.Mapper
import pimonitor.core.dashboards.OperationalDashboard
import presenters.cards.ValueCard
import presenters.charts.Chart
import kotlin.collections.List
import kotlin.collections.Map
import kotlin.collections.filter
import kotlin.collections.map
import kotlinx.collections.interoperable.List as IList

internal class PiCortexDashboardParser(val mapper: Mapper) {
    constructor(json: Json = PiCortexDashboardProviderConfig.DEFAULT_JSON) : this(Mapper(json))

    companion object {
        private const val REPORT_TYPE_SINGLE_VALUE = "SINGLE_VALUE"

        private const val CHART_TYPE_BAR = "bar"
    }

    private fun currencyReportsPair(json: String): Pair<Currency, List<Map<String, Any>>> {
        val map = mapper.decodeFromString(json)
        if (map.error != null) {
            throw RuntimeException("PiCortex Dashboard responded with an error -> ${map["message"]}")
        }
        return Currency.valueOf(map.currency) to map["reports"] as List<Map<String, Any>>
    }

    private inline val Map<String, *>.config get() = this["config"] as Map<String, *>
    private inline val Map<String, *>.dataClass get() = this["dataClass"] as String
    private inline val Map<String, *>.reportType get() = this["reportType"] as? String
    private inline val Map<String, *>.description get() = this["description"] as? String ?: dataClass
    private inline val Map<String, *>.name get() = this["name"] as String
    private inline val Map<String, *>.chartType get() = this["chartType"] as? String
    private inline val Map<String, *>.datasets get() = this["datasets"] as? List<Map<String, *>>
    private inline val Map<String, *>.labels get() = this["labels"] as List<String>
    private inline val Map<String, *>.label get() = this["label"] as String
    private inline val Map<String, *>.money get() = this["money"] as? Boolean
    private inline val Map<String, *>.data get() = this["data"] as Map<String, Number>
    private inline val Map<String, *>.tabularData get() = this["tabularData"] as? Map<String, Map<String, Double>>
    private inline val Map<String, *>.all get() = this["All"]?.toString()?.toDouble() ?: 0.0
    private inline val Map<String, *>.singleValueString get() = this["singleValueString"] as String
    private inline val Map<String, *>.singleValue get() = this["singleValue"] as Number
    private inline val Map<String, *>.currency get() = this["currency"] as String
    private inline val Map<String, *>.error get() = this["error"] as? String

    private fun parseBarChartsWithDataSets(reports: List<Map<String, *>>): List<Chart<Double>> = reports.filter {
        it.config.chartType == CHART_TYPE_BAR && it.datasets != null
    }.map {
        val config = it.config
        val datasets = it.datasets ?: listOf()
        val labels = it.labels.toInteroperableList()
        Chart(
            title = config.name,
            description = config.description,
            labels = labels,
            datasets = datasets.map { dataset ->
                val data = dataset.data
                Chart.DataSet(
                    name = dataset.label,
                    values = labels.map { label -> data[label]?.toDouble() ?: 0.0 }.toInteroperableList()
                )
            }.toInteroperableList()
        )
    }

    private fun parseBarMoneyChartsWithTabularData(reports: List<Map<String, *>>): List<Chart<Double>> = reports.filter {
        it.config.chartType == CHART_TYPE_BAR && it.tabularData != null
    }.map {
        val data = it.tabularData!!
        val labels = data.keys.toInteroperableList()
        Chart(
            title = it.config.name,
            description = it.config.description,
            labels = labels,
            datasets = listOf(
                Chart.DataSet(
                    name = it.config.name,
                    values = labels.map { label -> data[label]?.all ?: 0.0 }.toInteroperableList()
                )
            )
        )
    }

    private fun parseSingleNumberValues(reports: List<Map<String, *>>): IList<ValueCard<Double>> = reports.mapIndexed { index, map ->
        index to map
    }.filter { (_, map) ->
        map.config.reportType == REPORT_TYPE_SINGLE_VALUE && map.config.money != true
    }.map { (priority, map) ->
        ValueCard(
            title = map.config.name,
            value = map.singleValue.toDouble(),
            details = map.config.description,
            priority = priority
        )
    }.toInteroperableList()

    private fun parseSingleMoneyValues(currency: Currency, reports: List<Map<String, *>>): IList<ValueCard<Money>> = reports.mapIndexed { index, map ->
        index to map
    }.filter { (_, map) ->
        map.config.reportType == REPORT_TYPE_SINGLE_VALUE && map.config.money == true
    }.map { (priority, map) ->
        ValueCard(
            title = map.config.name,
            value = currency.of(map.singleValue.toDouble()),
            details = map.config.description,
            priority = priority
        )
    }.toInteroperableList()

    fun parseTechnicalDashboard(json: String): OperationalDashboard {
        val (currency, reports) = currencyReportsPair(json)
        val barCharts = parseBarChartsWithDataSets(reports)
        val tabularCharts = parseBarMoneyChartsWithTabularData(reports)
        return OperationalDashboard(
            moneyCards = parseSingleMoneyValues(currency, reports),
            numberCards = parseSingleNumberValues(reports),
            charts = (barCharts + tabularCharts).toInteroperableList()
        )
    }
}