package integration.authentication

import bitframe.authentication.SignInServiceKtor
import bitframe.authentication.signin.LoginCredentials
import expect.expect
import integration.CONFIGURATION_UNDER_TEST
import kotlinx.coroutines.runTest
import later.await
import kotlin.test.Ignore
import kotlin.test.Test

class SingInServiceKtor {
    private val service = SignInServiceKtor(CONFIGURATION_UNDER_TEST)

    @Test
    fun should_be_able_to_connect_to_a_server() = runTest {
        val credentials = LoginCredentials("user1@test.com", "pass1")
        val res = service.signIn(credentials).await()
        expect(res.spaces).toBeOfSize(1)
        expect(res.user.tag).toBe("testuser")
    }
}