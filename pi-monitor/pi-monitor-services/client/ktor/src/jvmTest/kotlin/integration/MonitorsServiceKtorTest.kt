package integration

import bitframe.testing.annotations.Lifecycle
import bitframe.testing.annotations.TestInstance
import bitframe.testing.annotations.Testcontainers
import pimonitor.client.monitors.MonitorsServiceKtor
import expect.expect
import kotlinx.coroutines.runTest
import later.await
import pimonitor.testing.PiMonitorIntegrationTest
import kotlin.test.Test

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
class MonitorsServiceKtorTest : PiMonitorIntegrationTest() {
    val service get() = piMonitorService.monitors

    @Test
    fun should_be_able_to_fetch_a_monitor_by_id() = runTest {
        val m = service.load("monitor-1").await()
        expect(m?.uid).toBe("monitor-1")
    }
}