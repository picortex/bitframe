package unit

import bitframe.actors.users.UserEmail
import expect.expect
import kotlinx.coroutines.test.runTest
import later.await
import pimonitor.api.PiMonitorService
import pimonitor.api.PiMonitorServiceMock
import pimonitor.authentication.signup.SignUpParams
import kotlin.test.Test

class AuthFlowTest {
    val service: PiMonitorService = PiMonitorServiceMock()

    @Test
    fun should_register_a_monitor_as_a_valid_user() = runTest {
        val params = SignUpParams.Individual(
            name = "John Doe",
            email = "john@doe.com",
            password = "john@doe.com"
        )
        val res = service.signUp.signUp(params).await()
        val contact = res.user.contacts.first() as UserEmail
        expect(contact.value).toBe("john@doe.com")
    }
}