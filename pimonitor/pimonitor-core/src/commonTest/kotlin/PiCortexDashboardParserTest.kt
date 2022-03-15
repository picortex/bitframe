import expect.expect
import kotlinx.serialization.json.Json
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.picortex.PiCortexDashboardParser
import kotlin.test.Test

class PiCortexDashboardParserTest {
    @Test
    fun should_parse_to_valid_dashboard() {
        val parser = PiCortexDashboardParser()
        val dashboard = parser.parseTechnicalDashboard(response)
//        val serializer = ListSerializer(BarChart.serializer(Double.serializer()))
//        println(Json.encodeToString(serializer, dashboard.charts))
        println(Json.encodeToString(OperationalDashboard.serializer(), dashboard))
        expect(dashboard.charts).toBeOfSize(10)
    }
}