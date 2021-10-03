package integration

import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.signin.SignInServiceKtor
import bitframe.authentication.signin.SignInServiceTest
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.UsersDaoInMemory
import bitframe.service.config.KtorClientConfiguration
import testing.annotations.Lifecycle
import testing.annotations.TestInstance
import testing.annotations.Testcontainers

@Testcontainers
@TestInstance(Lifecycle.PER_CLASS)
class SignInServiceIntegrationTest : SignInServiceTest() {
    override val service: SignInService by lazy {
        when (val cfg = config) {
            is KtorClientConfiguration -> SignInServiceKtor(cfg)
            else -> SignInServiceImpl(UsersDaoInMemory(), SpacesDaoInMemory(), cfg)
        }
    }
}