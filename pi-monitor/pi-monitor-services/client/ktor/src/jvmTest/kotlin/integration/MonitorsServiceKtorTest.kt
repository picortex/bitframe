package integration

import pimonitor.client.monitors.MonitorsServiceKtor
import expect.expect
import kotlinx.coroutines.runTest
import later.await
import kotlin.test.Test

class MonitorsServiceKtorTest {
    val service = MonitorsServiceKtor(CONFIG_UNDER_TEST)

    @Test
    fun should_be_able_to_fetch_a_monitor_by_id() = runTest {
        val m = service.load("monitor-1").await()
        expect(m?.uid).toBe("monitor-1")
    }
}