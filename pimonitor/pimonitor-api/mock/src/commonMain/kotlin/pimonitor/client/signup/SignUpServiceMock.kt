package pimonitor.client.signup

import bitframe.client.MockServiceConfig
import pimonitor.core.signup.SignUpDaodUseCase
import pimonitor.core.signup.SignUpUseCase

class SignUpServiceMock(
    override val config: MockServiceConfig
) : SignUpService(config), SignUpUseCase by SignUpDaodUseCase(config)