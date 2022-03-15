package charts

import kotlinx.collections.interoperable.listOf
import presenters.charts.Chart
import kotlin.test.Test

class BarChartTest {
    @Test
    fun should_easily_create_with_values() {
        val chart = Chart(
            title = "Chart Test",
            description = "A chart for testing",
            datasets = listOf(
                Chart.Entry("Papaya", 11),
                Chart.Entry("Warm Bread", 10)
            )
        )
        show(chart)
    }

}