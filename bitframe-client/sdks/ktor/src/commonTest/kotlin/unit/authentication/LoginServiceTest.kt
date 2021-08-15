package unit.authentication

import bitframe.authentication.KtorLoginService
import bitframe.authentication.LoginCredentials
import bitframe.authentication.LoginService
import expect.expect
import later.await
import test.asyncTest
import unit.utils.DEV_CONFIGURATION
import kotlin.test.Test

class LoginServiceTest {
    val service: LoginService = KtorLoginService(DEV_CONFIGURATION)

    @Test
    fun should_give_a_user_with_valid_credentials_access() = asyncTest {
        val conundrum = service.loginWith(LoginCredentials("user1", "pass1")).await()
        expect(conundrum.accounts).toBeOfSize(1)
    }

    @Test
    fun should_not_give_a_user_with_valid_credentials_access() = asyncTest {
        try {
            service.loginWith(LoginCredentials("username", "password")).await()
        } catch (err: Throwable) {
            expect(err.message?.contains("Invalid Credentials")).toBe(true)
        }
    }
}