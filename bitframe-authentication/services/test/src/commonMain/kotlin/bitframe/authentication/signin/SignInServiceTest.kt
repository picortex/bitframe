package bitframe.authentication.signin

import bitframe.authentication.InMemoryAuthenticationDaoProvider
import bitframe.events.InMemoryEventBus
import bitframe.service.config.KtorClientConfiguration
import cache.MockCache
import expect.expect
import expect.expectFailure
import kotlinx.coroutines.runTest
import later.await
import testing.IntegrationTest
import testing.annotations.Lifecycle
import testing.annotations.TestInstance
import testing.annotations.Testcontainers
import kotlin.test.Test

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
open class SignInServiceTest : IntegrationTest() {
    val provider = InMemoryAuthenticationDaoProvider()
    open val service: SignInService by lazy {
        val bus = InMemoryEventBus()
        val cache = MockCache()
        when (val cfg = config) {
            is KtorClientConfiguration -> SignInServiceKtor(cfg, cache, bus)
            else -> SignInServiceImpl(provider, cfg, cache, bus)
        }
    }

    @Test
    fun should_fail_when_credentials_are_empty() = runTest {
        val credentials = SignInCredentials("", "")
        val err = expectFailure {
            service.signIn(credentials).await()
        }
        expect(err.message).toBe("loginId (i.e. email/phone/username), must not be empty")
    }

    @Test
    fun should_fail_when_sign_in_alias_password_is_empty() = runTest {
        val credentials = SignInCredentials("john", "")
        val err = expectFailure {
            service.signIn(credentials).await()
        }
        expect(err.message).toBe("Password must not be empty")
    }

    @Test
    fun should_give_a_user_with_valid_credentials_access() = runTest {
        val conundrum = service.signIn(SignInCredentials("user1@test.com", "pass1")).await()
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