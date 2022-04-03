package pimonitor.client.signup

import bitframe.client.ServiceConfigMock
import pimonitor.core.signup.SignUpServiceDaod
import pimonitor.core.signup.SignUpServiceCore as CoreSignUpService

class SignUpServiceMock(
    val config: ServiceConfigMock
) : SignUpService(config), CoreSignUpService by SignUpServiceDaod(config)