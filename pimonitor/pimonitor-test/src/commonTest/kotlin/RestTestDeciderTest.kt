import expect.expect
import kotlinx.coroutines.test.runTest
import pimonitor.client.RestTestDecider
import pimonitor.client.TestDecider
import pimonitor.client.TestType
import kotlin.test.Test

class RestTestDeciderTest {
    @Test
    fun should_decide_unit_test_when_the_url_returns_nothing() = runTest {
        val decider: TestDecider = RestTestDecider("http://localhost:9090/should_test")
        expect(decider.decide()).toBe(TestType.UNIT)
    }

    @Test
    fun should_choose_integration_test_when_the_url_can_be_reached() = runTest {
        val decider: TestDecider = RestTestDecider("https://www.typescriptlang.org")
        expect(decider.decide()).toBe(TestType.INTEGRATION)
    }
}