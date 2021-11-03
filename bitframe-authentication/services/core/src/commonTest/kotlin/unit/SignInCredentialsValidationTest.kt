package unit

import bitframe.authentication.signin.SignInCredentials
import expect.expect
import expect.expectFailure
import expect.toBe
import kotlinx.coroutines.runTest
import later.await
import validation.Validation
import validation.Validation.Invalid
import validation.Validation.Valid
import kotlin.test.Test

open class SignInCredentialsValidationTest {
    val service = UnitTestSignInService()

    @Test
    fun should_fail_when_credentials_are_empty() = runTest {
        val credentials = SignInCredentials("", "")
        val validation = service.validate(credentials)
        expect(validation).toBe<Invalid>()
        val invalid = validation as Invalid
        expect(invalid.cause.message).toBe("loginId (i.e. email/phone/username), must not be empty")
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