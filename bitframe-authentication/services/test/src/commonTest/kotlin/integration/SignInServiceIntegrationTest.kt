package integration

import bitframe.authentication.InMemoryAuthenticationDaoProvider
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.signin.SignInServiceKtor
import bitframe.authentication.signin.SignInServiceTest
import bitframe.events.InMemoryEventBus
import bitframe.service.config.KtorClientConfiguration
import cache.MockCache
import expect.expect
import kotlin.test.Test

class SignInServiceIntegrationTest : SignInServiceTest() {
    override val service: SignInService by lazy {
        val bus = InMemoryEventBus()
        val cache = MockCache()
        when (val cfg = config) {
            is KtorClientConfiguration -> SignInServiceKtor(cfg, cache, bus)
            else -> SignInServiceImpl(InMemoryAuthenticationDaoProvider(), cfg, cache, bus)
        }
    }

    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }
}