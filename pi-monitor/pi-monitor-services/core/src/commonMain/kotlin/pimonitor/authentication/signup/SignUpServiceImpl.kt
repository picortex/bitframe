package pimonitor.authentication.signup

import bitframe.authentication.apps.App
import bitframe.authentication.users.UsersService
import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import later.later
import pimonitor.monitors.MonitorDao
import pimonitor.monitors.SignUpParams
import pimonitor.monitors.toRegisterSpaceParams
import pimonitor.monitors.toRegisterUserParams

class SignUpServiceImpl(
    private val dao: MonitorDao,
    private val usersService: UsersService,
    override val bus: EventBus,
    override val config: ServiceConfig
) : SignUpService(bus, config) {
    override fun executeSignUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        val register = when (params) {
            is SignUpParams.Individual -> usersService.register(params.toRegisterUserParams())
            is SignUpParams.Business -> usersService.registerWithSpace(
                user = params.toRegisterUserParams(), space = params.toRegisterSpaceParams()
            )
        }.await()
        val monitor = dao.create(params, register.user.ref()).await()
        SignUpResult(
            app = App(config.appId), space = register.spaces.first(), user = register.user, monitor = monitor
        )
    }
}