package integration.authentication

import bitframe.authentication.SignInServiceKtor
import bitframe.authentication.LoginCredentials
import bitframe.authentication.SignInService
import expect.expect
import integration.ktor.utils.CONFIGURATION_UNDER_TEST
import kotlinx.coroutines.runTest
import later.await
import kotlin.test.Ignore
import kotlin.test.Test

class LoginServiceTest {
    val service: SignInService = SignInServiceKtor(CONFIGURATION_UNDER_TEST)

    @Test
    @Ignore
    fun should_give_a_user_with_valid_credentials_access() = runTest {
        val conundrum = service.signIn(LoginCredentials("user1", "pass1")).await()
        expect(conundrum.accounts).toBeOfSize(1)
    }

    @Test
    @Ignore
    fun should_not_give_a_user_with_valid_credentials_access() = runTest {
        try {
            service.signIn(LoginCredentials("username", "password")).await()
        } catch (err: Throwable) {
            expect(err.message?.contains("Invalid Credentials")).toBe(true)
        }
    }
}