package signup

import expect.expect
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.client.api
import pimonitor.core.signup.SignUpParams
import kotlin.test.Test

class AuthFlowTest {
    //    val service: PiMonitorApi = PiMonitorApiMock()
    val api = api {
        appId = "TEST_ID"
        url = "http://127.0.0.1"
    }

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