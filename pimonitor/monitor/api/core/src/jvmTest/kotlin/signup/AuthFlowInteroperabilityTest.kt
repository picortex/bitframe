package signup

import expect.expect
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.MonitorApi
import pimonitor.client.MonitorApiTest
import pimonitor.core.signup.params.SignUpIndividualParams
import kotlin.test.Test

class AuthFlowInteroperabilityTest {
    val service: MonitorApi = MonitorApiTest()

    @Test
    fun should_register_a_monitor_as_a_valid_user() = runTest {
        val time = Clock.System.now()
        val params = SignUpIndividualParams(
            name = "John Doe $time",
            email = "john@doe$time.com",
            password = "john@doe$time.com"
        )
        val res = service.signUp.signUp(params).await()
        expect(res.user.name).toBe("John Doe $time")
    }
}