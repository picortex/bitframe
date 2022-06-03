package signup

import expect.expect
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.Clock
import later.await
import pimonitor.client.MonitorApiTest
import pimonitor.core.signup.params.SignUpBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams
import kotlin.test.Test

class AuthFlowTest {
    val api = MonitorApiTest()

    @Test
    fun should_register_an_individual_monitor_as_a_valid_user() = runTest {
        val time = Clock.System.now()
        val params = SignUpIndividualParams(
            name = "John Doe $time",
            email = "john@doe$time.com",
            password = "john@doe.com"
        )
        val res = api.signUp.signUp(params).await()
        expect(res.user.name).toBe("John Doe $time")
    }

    @Test
    fun should_register_a_business_monitor_as_a_valid_user() = runTest {
        val time = Clock.System.now()
        val params = SignUpBusinessParams(
            businessName = "John Doe Inc $time",
            individualName = "John Doe $time",
            individualEmail = "john@doe$time.com",
            password = "john@doe$time.com"
        )
        val res = api.signUp.signUp(params).await()
        expect(res.user.name).toBe("John Doe $time")
    }
}