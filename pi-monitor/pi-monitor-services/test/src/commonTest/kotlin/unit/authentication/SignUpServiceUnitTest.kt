package unit.authentication

import bitframe.authentication.signup.SignUpServiceTest
import bitframe.testing.annotations.Lifecycle
import bitframe.testing.annotations.TestInstance
import bitframe.testing.annotations.Testcontainers
import expect.expect
import kotlin.test.Test

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
class SignUpServiceUnitTest : SignUpServiceTest() {
    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }
}