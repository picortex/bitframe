package pimonitor.client.signup

import bitframe.client.MockServiceConfig
import pimonitor.core.signup.SignUpDaodService
import pimonitor.core.signup.SignUpService as CoreSignUpService

class SignUpServiceMock(
    override val config: MockServiceConfig
) : SignUpService, CoreSignUpService by SignUpDaodService(config)