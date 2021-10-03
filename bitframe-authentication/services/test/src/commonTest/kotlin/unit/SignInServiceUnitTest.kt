package unit

import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.signin.SignInServiceKtor
import bitframe.authentication.signin.SignInServiceTest
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.UsersDaoInMemory
import bitframe.service.config.KtorClientConfiguration
import bitframe.service.config.ServiceConfig

class SignInServiceUnitTest : SignInServiceTest() {
    override val service: SignInService by lazy {
        SignInServiceImpl(UsersDaoInMemory(), SpacesDaoInMemory(), ServiceConfig.DEFAULT)
    }
}