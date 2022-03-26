package pimonitor.client.signup

import bitframe.client.ServiceConfigMock
import pimonitor.core.signup.SignUpServiceDaod
import pimonitor.core.signup.SignUpService as CoreSignUpService

class SignUpServiceMock(
    override val config: ServiceConfigMock
) : SignUpService, CoreSignUpService by SignUpServiceDaod(config)