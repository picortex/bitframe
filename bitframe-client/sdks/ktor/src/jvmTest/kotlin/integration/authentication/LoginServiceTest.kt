package integration.authentication

import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceKtor
import expect.expect
import integration.ktor.utils.IntegrationTest
import kotlinx.coroutines.runTest
import later.await
import org.junit.jupiter.api.TestInstance
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Test

@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoginServiceTest : IntegrationTest() {
    val service: SignInService by lazy { SignInServiceKtor(config) }

    @Test
    fun should_give_a_user_with_valid_credentials_access() = runTest {
        val conundrum = service.signIn(SignInCredentials("user1", "pass1")).await()
        expect(conundrum.spaces).toBeOfSize(1)
    }

    @Test
    fun should_not_give_a_user_with_valid_credentials_access() = runTest {
        try {
            service.signIn(SignInCredentials("username", "password")).await()
        } catch (err: Throwable) {
            expect(err.message?.contains("User with loginId=username, not found")).toBe(true)
        }
    }
}