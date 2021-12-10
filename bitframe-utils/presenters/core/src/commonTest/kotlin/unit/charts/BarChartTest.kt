package unit.charts

import kotlinx.collections.interoperable.listOf
import presenters.charts.BarChart
import kotlin.test.Test

class BarChartTest {
    @Test
    fun should_easily_create_with_values() {
        val chart = BarChart(
            title = "Chart Test",
            description = "A chart for testing",
            entries = listOf(
                BarChart.Entry("Papaya", 11),
                BarChart.Entry("Warm Bread", 10)
            )
        )
        show(chart)
    }

}