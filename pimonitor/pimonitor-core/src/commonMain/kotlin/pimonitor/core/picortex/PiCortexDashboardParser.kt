package pimonitor.core.picortex

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
import kotlin.collections.firstOrNull
import kotlin.collections.map
import kotlin.collections.mapOf
import kotlinx.collections.interoperable.List as IList

internal class PiCortexDashboardParser(val mapper: Mapper) {
    constructor(json: Json = PiCortexDashboardProviderConfig.DEFAULT_JSON) : this(Mapper(json))

    companion object {
        private const val REPORT_TYPE_SINGLE_VALUE = "SINGLE_VALUE"

        private const val CHART_TYPE_BAR = "bar"
    }

    private fun reports(json: String): List<Map<String, Any>> {
        val map = mapper.decodeFromString(json)
        return map["reports"] as List<Map<String, Any>>
    }

    private inline val Map<String, *>.config get() = this["config"] as Map<String, *>
    private inline val Map<String, *>.reportType get() = this["reportType"] as? String
    private inline val Map<String, *>.description get() = this["description"] as String
    private inline val Map<String, *>.name get() = this["name"] as String
    private inline val Map<String, *>.chartType get() = this["chartType"] as? String
    private inline val Map<String, *>.datasets get() = this["datasets"] as? List<Map<String, *>>
    private inline val Map<String, *>.labels get() = this["labels"] as List<String>
    private inline val Map<String, *>.label get() = this["label"] as String
    private inline val Map<String, *>.data get() = this["data"] as Map<String, Number>
    private inline val Map<String, *>.tabularData get() = this["tabularData"] as? Map<String, Map<String, Double>>
    private inline val Map<String, *>.all get() = this["All"]?.toString()?.toDouble() ?: 0.0
    private inline val Map<String, *>.singleValueString get() = this["singleValueString"] as String

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

    private fun parseBarChartsWithTabularData(reports: List<Map<String, *>>): List<Chart<Double>> = reports.filter {
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

    private fun parseSingleValues(reports: List<Map<String, *>>): IList<ValueCard<String>> = reports.filter {
        it.config.reportType == REPORT_TYPE_SINGLE_VALUE
    }.map {
        ValueCard(
            title = it.config.name,
            value = it.singleValueString,
            details = it.config.description
        )
    }.toInteroperableList()

    fun parseTechnicalDashboard(json: String): OperationalDashboard {
        val reports = reports(json)
        val barCharts = parseBarChartsWithDataSets(reports)
        val tabularCharts = parseBarChartsWithTabularData(reports)
        return OperationalDashboard(
            cards = parseSingleValues(reports),
            charts = (barCharts + tabularCharts).toInteroperableList()
        )
    }
}