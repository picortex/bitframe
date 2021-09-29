package unit

import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.signin.LoginCredentials
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
        val res = client.signIn.signIn(
            LoginCredentials(
                alias = "user01@test.com",
                password = "pass1"
            )
        )
        expect(res.await().user.tag).toBe("user01")
    }
}