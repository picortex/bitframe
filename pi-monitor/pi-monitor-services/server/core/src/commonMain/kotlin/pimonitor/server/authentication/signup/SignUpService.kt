package pimonitor.server.authentication.signup

import bitframe.authentication.apps.App
import bitframe.authentication.server.users.usecases.RegisterUserImpl
import bitframe.authentication.users.usecases.RegisterUser
import bitframe.daos.get
import bitframe.service.server.config.ServiceConfig
import later.Later
import later.await
import later.later
import pimonitor.authentication.signup.*
import pimonitor.authentication.signup.SignUpService
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor
import pimonitor.monitors.Monitor

class SignUpService(
    override val config: ServiceConfig
) : SignUpService(config), RegisterUser by RegisterUserImpl(config) {
    private val scope get() = config.scope
    private val individualMonitorsDao get() = config.daoFactory.get<IndividualMonitor>()
    private val cooperateMonitorsDao get() = config.daoFactory.get<CooperateMonitor>()
    override fun executeSignUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        val register = when (params) {
            is SignUpParams.Individual -> register(params.toRegisterUserParams())
            is SignUpParams.Business -> registerWithSpace(
                user = params.toRegisterUserParams(), space = params.toCreateSpaceParams()
            )
        }.await()

        val ref = register.user.ref()
        val monitor = when (params) {
            is SignUpParams.Individual -> individualMonitorsDao.create(params.toMonitor("", ref))
            is SignUpParams.Business -> cooperateMonitorsDao.create(params.toMonitor("", ref))
        }.await()

        SignUpResult(
            app = App(""), space = register.spaces.first(), user = register.user, monitor = monitor
        )
    }
}