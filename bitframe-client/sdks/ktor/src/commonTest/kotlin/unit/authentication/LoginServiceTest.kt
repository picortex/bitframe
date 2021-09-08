package unit.authentication

import bitframe.authentication.KtorSignInService
import bitframe.authentication.LoginCredentials
import bitframe.authentication.SignInService
import expect.expect
import kotlinx.coroutines.runTest
import later.await
import unit.utils.CONFIGURATION_UNDER_TEST
import kotlin.test.Test

class LoginServiceTest {
    val service: SignInService = KtorSignInService(CONFIGURATION_UNDER_TEST)

    @Test
    fun should_give_a_user_with_valid_credentials_access() = runTest {
        val conundrum = service.loginWith(LoginCredentials("user1", "pass1")).await()
        expect(conundrum.accounts).toBeOfSize(1)
    }

    @Test
    fun should_not_give_a_user_with_valid_credentials_access() = runTest {
        try {
            service.loginWith(LoginCredentials("username", "password")).await()
        } catch (err: Throwable) {
            expect(err.message?.contains("Invalid Credentials")).toBe(true)
        }
    }
}