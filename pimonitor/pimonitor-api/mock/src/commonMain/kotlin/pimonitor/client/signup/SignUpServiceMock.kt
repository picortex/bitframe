package pimonitor.client.signup

import bitframe.client.ServiceConfigMock
import pimonitor.core.signup.SignUpDaodService
import pimonitor.core.signup.SignUpService as CoreSignUpService

class SignUpServiceMock(
    override val config: ServiceConfigMock
) : SignUpService, CoreSignUpService by SignUpDaodService(config)