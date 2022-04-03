import bitframe.client.signin.SignInServiceMock
import bitframe.core.signin.SignInRawParams
import bitframe.core.signin.SignInServiceCore
import expect.expect
import expect.expectFailure
import expect.toBe
import kotlinx.coroutines.test.runTest
import validation.Invalid
import kotlin.test.Test

open class SignInCredentialsValidationTest {
    val service: SignInServiceCore = SignInServiceMock()

    @Test
    fun should_fail_when_credentials_are_empty() = runTest {
        val credentials = SignInRawParams("", "")
        val validation = service.validate(credentials)
        expect(validation).toBe<Invalid>()
        val invalid = validation as Invalid
        expect(invalid.cause.message).toBe("login identifier (i.e. email/phone), must not be empty")
    }

    @Test
    fun should_fail_when_sign_in_alias_password_is_empty() = runTest {
        val credentials = SignInRawParams("john", "")
        val validation = service.validate(credentials)
        expect(validation).toBe<Invalid>()
        val err = expectFailure {
            validation.throwIfInvalid()
        }
        expect(err.message).toBe("Password must not be empty")
    }
}