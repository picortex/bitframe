import expect.expect
import pimonitor.client.PiMonitorAppScope
import utils.PiMonitorTestScope
import kotlin.test.Test

class PiMonitorAppScopeInteroperabilityTest {
    val scope: PiMonitorAppScope = PiMonitorTestScope()

    @Test
    fun should_be_able_to_get_api_from_pi_monitor() {
        expect(scope.config.api).toBeNonNull()
    }
}