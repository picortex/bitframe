package pimonitor.client

import bitframe.service.client.config.ServiceConfig
import bitframe.service.requests.RequestBody
import later.Later
import pimonitor.authentication.signup.SignUpParams
import pimonitor.authentication.signup.SignUpResult
import pimonitor.client.authentication.signup.SignUpService

class SignUpServiceMock(override val config: ServiceConfig) : SignUpService(config) {
    override fun signUp(rb: RequestBody.UnAuthorized<SignUpParams>): Later<SignUpResult> {
        TODO("Not yet implemented")
    }
}