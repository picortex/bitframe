package unit

import bitframe.authentication.client.signin.SignInServiceMock
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInService
import expect.expect
import expect.expectFailure
import expect.toBe
import kotlinx.coroutines.test.runTest
import validation.Invalid
import kotlin.test.Test

open class SignInCredentialsValidationTest {
    val service: SignInService = SignInServiceMock()

    @Test
    fun should_fail_when_credentials_are_empty() = runTest {
        val credentials = SignInCredentials("", "")
        val validation = service.validate(credentials)
        expect(validation).toBe<Invalid>()
        val invalid = validation as Invalid
        expect(invalid.cause.message).toBe("login identifier (i.e. email/phone), must not be empty")
    }

    @Test
    fun should_fail_when_sign_in_alias_password_is_empty() = runTest {
        val credentials = SignInCredentials("john", "")
        val validation = service.validate(credentials)
        expect(validation).toBe<Invalid>()
        val err = expectFailure {
            validation.throwIfInvalid()
        }
        expect(err.message).toBe("Password must not be empty")
    }
}