package pimonitor.client

import bitframe.service.config.ServiceConfig
import later.Later
import pimonitor.authentication.signup.SignUpParams
import pimonitor.authentication.signup.SignUpResult
import pimonitor.authentication.signup.SignUpService

class SignUpServiceMock(override val config: ServiceConfig) : SignUpService(config) {
    override fun executeSignUp(params: SignUpParams): Later<SignUpResult> {
        TODO("Not yet implemented")
    }
}