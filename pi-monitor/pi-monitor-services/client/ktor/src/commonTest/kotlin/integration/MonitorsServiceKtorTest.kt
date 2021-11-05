package integration

import bitframe.authentication.client.signin.SignInServiceKtor
import expect.expect
import kotlinx.coroutines.runTest
import later.await
import pimonitor.client.monitors.MonitorsServiceKtor
import kotlin.test.Test

class MonitorsServiceKtorTest {
    private val signInService = SignInServiceKtor(CONFIG_UNDER_TEST)

    val service = MonitorsServiceKtor(CONFIG_UNDER_TEST.with(signInService))

    @Test
    fun should_be_able_to_fetch_a_monitor_by_id() = runTest {
        val m = service.load("monitor-1").await()
        expect(m?.uid).toBe("monitor-1")
    }
}