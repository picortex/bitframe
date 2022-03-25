import expect.expect
import kotlinx.collections.interoperable.emptyList
import kotlinx.collections.interoperable.listOf
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.picortex.PiCortexDashboardParser
import presenters.cards.ValueCard
import presenters.charts.Chart
import kotlin.test.Ignore
import kotlin.test.Test

class PiCortexDashboardParserTest {
    @Test
    fun should_serialize_dashboard() {
        val dashboard = OperationalDashboard(
            moneyCards = emptyList(),
            numberCards = listOf(ValueCard("Test", 2.0, "Test")),
            charts = listOf(Chart("Test", "Test", emptyList(), emptyList())),
        )
        val json = Json.encodeToString(dashboard)
        println(json)
    }

    @Test
    fun should_parse_charts() {
        val parser = PiCortexDashboardParser()
        val dashboard = parser.parseTechnicalDashboard(response)
        expect(dashboard.charts).toBeOfSize(6)
    }

    @Test
    fun should_parse_cards() {
        val parser = PiCortexDashboardParser()
        val dashboard = parser.parseTechnicalDashboard(response)
        expect(dashboard.cards).toBeOfSize(8)
    }

    @Test
    @Ignore
    fun should_parse_customer_data_by_location_on_map() {
        TODO("Find a way to also show customer data")
    }
}