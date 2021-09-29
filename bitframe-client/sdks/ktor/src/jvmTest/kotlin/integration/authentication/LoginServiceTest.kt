package integration.authentication

import bitframe.authentication.SignInServiceKtor
import bitframe.authentication.signin.LoginCredentials
import bitframe.authentication.signin.SignInService
import expect.expect
import integration.ktor.utils.IntegrationTest
import kotlinx.coroutines.runTest
import later.await
import org.junit.jupiter.api.TestInstance
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Ignore
import kotlin.test.Test

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginServiceTest : IntegrationTest() {
    val service: SignInService by lazy { SignInServiceKtor(config) }

    @Test
    fun should_give_a_user_with_valid_credentials_access() = runTest {
        val conundrum = service.signIn(LoginCredentials("user1", "pass1")).await()
        expect(conundrum.spaces).toBeOfSize(1)
    }

    @Test
    fun should_not_give_a_user_with_valid_credentials_access() = runTest {
        try {
            service.signIn(LoginCredentials("username", "password")).await()
        } catch (err: Throwable) {
            expect(err.message?.contains("User with loginId=username, not found")).toBe(true)
        }
    }
}