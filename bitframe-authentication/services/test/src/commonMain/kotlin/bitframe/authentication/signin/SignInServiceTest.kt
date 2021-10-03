package bitframe.authentication.signin

import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.UsersDaoInMemory
import bitframe.service.config.KtorClientConfiguration
import expect.expect
import kotlinx.coroutines.runTest
import later.await
import testing.IntegrationTest
import testing.annotations.Lifecycle
import testing.annotations.TestInstance
import testing.annotations.Testcontainers
import kotlin.test.Test

open class SignInServiceTest : IntegrationTest() {
    open val service: SignInService by lazy {
        when (val cfg = config) {
            is KtorClientConfiguration -> SignInServiceKtor(cfg)
            else -> SignInServiceImpl(UsersDaoInMemory(), SpacesDaoInMemory(), cfg)
        }
    }

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