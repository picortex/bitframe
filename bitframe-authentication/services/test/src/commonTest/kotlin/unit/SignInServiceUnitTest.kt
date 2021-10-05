package unit

import bitframe.authentication.InMemoryAuthenticationDaoProvider
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.signin.SignInServiceKtor
import bitframe.authentication.signin.SignInServiceTest
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.UsersDaoInMemory
import bitframe.service.config.KtorClientConfiguration
import bitframe.service.config.ServiceConfig
import expect.expect
import expect.toBe
import testing.pimonitor.APP_ID
import kotlin.test.Test

class SignInServiceUnitTest : SignInServiceTest() {
    override val service: SignInService by lazy {
        SignInServiceImpl(InMemoryAuthenticationDaoProvider(), ServiceConfig(APP_ID))
    }

    @Test
    fun should_run_with_sign_in_service_impl() {
        expect(service).toBe<SignInServiceImpl>()
    }
}