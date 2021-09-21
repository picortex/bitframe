package unit

import bitframe.authentication.LoginCredentials
import bitframe.authentication.TestClientConfiguration
import expect.expect
import kotlinx.coroutines.runTest
import later.await
import pimonitor.PiMonitorService
import pimonitor.test.PiMonitorServiceTest
import kotlin.test.Test

class ExhaustionTest {
    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }

    @Test
    fun should_sign_up() = runTest {
        val configuration = TestClientConfiguration(
            appId = "12345"
        )
        val client: PiMonitorService = PiMonitorServiceTest(configuration)
        val res = client.signIn.loginWith(
            LoginCredentials(
                alias = "user1",
                password = "pass1"
            )
        )
        expect(res.await().user.username).toBe("user1")
    }
}