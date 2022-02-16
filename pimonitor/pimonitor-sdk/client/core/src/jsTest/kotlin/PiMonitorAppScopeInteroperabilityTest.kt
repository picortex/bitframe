import expect.expect
import pimonitor.client.PiMonitorAppScope
import utils.PiMonitorMockScope
import kotlin.test.Test

class PiMonitorAppScopeInteroperabilityTest {
    val scope: PiMonitorAppScope = PiMonitorMockScope()

    @Test
    fun should_be_able_to_get_api_from_pi_monitor() {
        expect(scope.config.api).toBeNonNull()
    }

    @Test
    fun config_should_not_be_null() {
        println("Is config null: ${scope.config == null}")
        expect(scope.config).toBeNonNull()
    }
}