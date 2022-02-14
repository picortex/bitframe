package unit

import expect.expect
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.api.PiMonitorApi
import pimonitor.api.PiMonitorApiMock
import pimonitor.authentication.signup.SignUpParams
import kotlin.test.Test

class AuthFlowTest {
    val service: PiMonitorApi = PiMonitorApiMock()

    @Test
    fun should_register_a_monitor_as_a_valid_user() = runTest {
        val params = SignUpParams.Individual(
            name = "John Doe",
            email = "john@doe.com",
            password = "john@doe.com"
        )
        val res = service.signUp.signUp(params).await()
        expect(res.user.name).toBe("John Doe")
    }
}