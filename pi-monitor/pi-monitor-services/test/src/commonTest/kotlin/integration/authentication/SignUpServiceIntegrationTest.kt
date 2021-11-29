package integration.authentication

import bitframe.authentication.signup.SignUpServiceTest
import expect.expect
import kotlin.test.Test

class SignUpServiceIntegrationTest : SignUpServiceTest() {

    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }
}