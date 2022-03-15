import expect.expect
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import pimonitor.core.picortex.PiCortexDashboardParser
import kotlin.test.Ignore
import kotlin.test.Test

class PiCortexDashboardParserTest {
    @Test
    fun should_parse_charts() {
        val parser = PiCortexDashboardParser()
        val dashboard = parser.parseTechnicalDashboard(response)
        println(Json.encodeToString(dashboard))
        expect(dashboard.charts).toBeOfSize(10)
    }

    @Test
    fun should_parse_cards() {
        val parser = PiCortexDashboardParser()
        val dashboard = parser.parseTechnicalDashboard(response)
        println(Json.encodeToString(dashboard))
        expect(dashboard.cards).toBeOfSize(10)
    }

    @Test
    @Ignore
    fun should_parse_customer_data_by_location_on_map() {
        TODO("Find a way to also show customer data")
    }
}