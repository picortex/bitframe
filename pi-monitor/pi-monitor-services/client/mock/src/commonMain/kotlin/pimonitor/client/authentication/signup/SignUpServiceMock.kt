package pimonitor.client.authentication.signup

import bitframe.client.MockServiceConfig
import pimonitor.authentication.signup.SignUpUseCase
import pimonitor.service.daod.signup.SignUpDaodUseCase

class SignUpServiceMock(
    override val config: MockServiceConfig
) : SignUpService(config), SignUpUseCase by SignUpDaodUseCase(config)