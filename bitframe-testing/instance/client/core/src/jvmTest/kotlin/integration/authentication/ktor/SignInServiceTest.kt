package integration.authentication.ktor

import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.signin.SignInCredentials
import expect.expect
import integration.ktor.utils.IntegrationTest
import kotlinx.coroutines.test.runTest
import later.await
import org.junit.jupiter.api.TestInstance
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore // TODO: Arrange testing properly
@Testcontainers
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
open class SignInServiceTest : IntegrationTest() {
    private val service: SignInService get() = IntegrationTest.service.signIn

    @Test
    fun should_give_a_user_with_valid_credentials_access() = runTest {
        val conundrum = service.signIn(SignInCredentials("ssajja@gmail.com", "pass1")).await()
        expect(conundrum.user.tag).toBe("ssajja")
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