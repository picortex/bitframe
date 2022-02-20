package signup

import expect.expect
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.client.PiMonitorApiTest
import pimonitor.client.api
import pimonitor.core.signup.SignUpParams
import kotlin.test.Test

class AuthFlowTest {
    val api = PiMonitorApiTest()

    @Test
    fun should_register_a_monitor_as_a_valid_user() = runTest {
        val params = SignUpParams.Individual(
            name = "John Doe",
            email = "john@doe.com",
            password = "john@doe.com"
        )
        val res = api.signUp.signUp(params).await()
        expect(res.user.name).toBe("John Doe")
    }
}