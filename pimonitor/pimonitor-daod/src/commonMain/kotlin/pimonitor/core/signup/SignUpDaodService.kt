package pimonitor.core.signup

import bitframe.core.DaodServiceConfig

open class SignUpDaodService(
    override val config: DaodServiceConfig
) : SignUpService(config), SignUpUseCase by SignUpDaodUseCase(config)