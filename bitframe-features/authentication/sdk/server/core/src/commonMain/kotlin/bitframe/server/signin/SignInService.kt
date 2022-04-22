package bitframe.server.signin

import bitframe.core.signin.SignInServiceCore
import bitframe.core.signin.SignInServiceDaod
import bitframe.server.ServiceConfig

class SignInService(
    val config: ServiceConfig
) : SignInServiceCore by SignInServiceDaod(config) {
    val parser by lazy { SignInParser(config) }
}