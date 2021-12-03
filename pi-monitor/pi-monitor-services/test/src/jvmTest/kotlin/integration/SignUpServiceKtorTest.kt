package integration

import expect.expect
import kotlinx.coroutines.runTest
import later.await
import pimonitor.authentication.signup.SignUpParams
import pimonitor.client.authentication.signup.SignUpServiceKtor
import bitframe.testing.annotations.Lifecycle
import bitframe.testing.annotations.TestInstance
import bitframe.testing.annotations.Testcontainers
import pimonitor.testing.PiMonitorIntegrationTest
import validation.ValidationError
import kotlin.test.Test

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
open class SignUpServiceKtorTest : PiMonitorIntegrationTest() {
    private val service get() = piMonitorService.signUp

    @Test
    fun should_successfully_register_a_user() = runTest {
        val result = service.signUp(SignUpParams.Individual("Anderson", "andylamax@gmail.com", "andylamax@gmail.com")).await()
        expect(result.user.tag).toBe("Anderson")
    }

    @Test
    fun should_throw_an_error_when_email_is_invalid() = runTest {
        try {
            service.signUp(SignUpParams.Individual("Juma", "juma", "juma")).await()
        } catch (err: ValidationError) {
            expect(err.message).toBe("Invalid email: juma")
        }
    }

    @Test
    fun should_throw_an_error_when_password_is_empty() = runTest {
        try {
            service.signUp(SignUpParams.Individual("Juma", "juma@jum.jum", "")).await()
        } catch (err: ValidationError) {
            expect(err.message).toBe("Password must not be empty")
        }
    }
}