package integration

import bitframe.authentication.InMemoryAuthenticationDaoProvider
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.signin.SignInServiceKtor
import bitframe.authentication.signin.SignInServiceTest
import bitframe.service.config.KtorClientConfiguration
import expect.expect
import kotlin.test.Test

class SignInServiceIntegrationTest : SignInServiceTest() {
    override val service: SignInService<*> by lazy {
        when (val cfg = config) {
            is KtorClientConfiguration -> SignInServiceKtor(cfg)
            else -> SignInServiceImpl(InMemoryAuthenticationDaoProvider(), cfg)
        }
    }

    @Test
    fun should_pass() {
        expect(1 + 1).toBe(2)
    }
}