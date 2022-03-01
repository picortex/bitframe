package signup

import expect.expect
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.PiMonitorApi
import pimonitor.client.PiMonitorApiMock
import pimonitor.client.PiMonitorApiTest
import pimonitor.core.signup.params.IndividualSignUpParams
import pimonitor.core.signup.params.IndividualSignUpRawParams
import kotlin.test.Test

class AuthFlowInteroperabilityTest {
    val service: PiMonitorApi = PiMonitorApiTest()

    @Test
    fun should_register_a_monitor_as_a_valid_user() = runTest {
        val time = Clock.System.now()
        val params = IndividualSignUpParams(
            name = "John Doe $time",
            email = "john@doe$time.com",
            password = "john@doe$time.com"
        )
        val res = service.signUp.signUp(params).await()
        expect(res.user.name).toBe("John Doe $time")
    }
}