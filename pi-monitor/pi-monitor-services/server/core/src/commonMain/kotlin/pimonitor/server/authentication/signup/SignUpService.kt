package pimonitor.server.authentication.signup

import bitframe.authentication.apps.App
import later.Later
import later.await
import later.later
import pimonitor.authentication.signup.*
import pimonitor.authentication.signup.SignUpService

class SignUpService(
    override val config: SignUpServiceConfig
) : SignUpService(config) {
    private val usersService get() = config.usersService
    private val scope get() = config.scope
    private val dao get() = config.dao
    override fun executeSignUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        val register = when (params) {
            is SignUpParams.Individual -> usersService.register(params.toRegisterUserParams())
            is SignUpParams.Business -> usersService.registerWithSpace(
                user = params.toRegisterUserParams(), space = params.toCreateSpaceParams()
            )
        }.await()
        val monitor = dao.create(params, register.user.ref()).await()
        SignUpResult(
            app = App(""), space = register.spaces.first(), user = register.user, monitor = monitor
        )
    }
}