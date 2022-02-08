package pimonitor.service.daod.signup

import bitframe.service.daod.config.DaodServiceConfig
import pimonitor.authentication.signup.SignUpUseCase
import pimonitor.authentication.signup.SignUpService as CoreSignUpService

open class SignUpService(
    override val config: DaodServiceConfig
) : CoreSignUpService(config), SignUpUseCase by SignUpDaodUseCase(config)