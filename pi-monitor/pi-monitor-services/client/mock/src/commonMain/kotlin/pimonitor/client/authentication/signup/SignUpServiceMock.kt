package pimonitor.client.authentication.signup

import bitframe.service.client.config.MockServiceConfig
import pimonitor.authentication.signup.SignUpUseCase
import pimonitor.client.authentication.signup.SignUpService
import pimonitor.service.daod.signup.SignUpDaodUseCase

class SignUpServiceMock(
    override val config: MockServiceConfig
) : SignUpService(config), SignUpUseCase by SignUpDaodUseCase(config)