package signup

import expect.expect
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiMock
import pimonitor.core.signup.params.IndividualSignUpRawParams
import kotlin.test.Test

class AuthFlowInteroperabilityTest {
    val service: PiMonitorApi = PiMonitorApiMock()

    @Test
    fun should_register_a_monitor_as_a_valid_user() = runTest {
        val params = object : IndividualSignUpRawParams {
            override val name = "John Doe 1"
            override val email = "john@doe1.com"
            override val password = "john@doe1.com"
        }
        val res = service.signUp.signUp(params).await()
        expect(res.user.name).toBe("John Doe 1")
    }
}