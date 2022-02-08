package pimonitor.server.authentication.signup

import bitframe.actors.apps.App
import bitframe.authentication.server.users.usecases.RegisterUserImpl
import bitframe.actors.users.usecases.RegisterUser
import bitframe.authentication.spaces.CreateSpaceParams
import bitframe.daos.get
import bitframe.service.requests.RequestBody
import bitframe.service.server.config.ServiceConfig
import later.Later
import later.await
import later.later
import pimonitor.authentication.signup.*
import pimonitor.authentication.signup.SignUpService as CoreSignUpService
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor

class SignUpService(
    override val config: ServiceConfig
) : CoreSignUpService(config), RegisterUser by RegisterUserImpl(config) {
    private val scope get() = config.scope
    private val individualMonitorsDao get() = config.daoFactory.get<IndividualMonitor>()
    private val cooperateMonitorsDao get() = config.daoFactory.get<CooperateMonitor>()

    public override fun signUp(rb: RequestBody.UnAuthorized<SignUpParams>) = scope.later {
        val params = rb.data
        val register = when (params) {
            is SignUpParams.Individual -> register(
                params.toRegisterUserParams(), CreateSpaceParams(
                    name = params.name,
                    type = params.name,
                    scope = params.name
                )
            )
            is SignUpParams.Business -> register(
                user = params.toRegisterUserParams(), space = params.toCreateSpaceParams()
            )
        }.await()

        val ref = register.user.ref()
        val monitor = when (params) {
            is SignUpParams.Individual -> individualMonitorsDao.create(params.toMonitor("", ref))
            is SignUpParams.Business -> cooperateMonitorsDao.create(params.toMonitor("", ref))
        }.await()

        SignUpResult(
            app = App(rb.appId), space = register.spaces.first(), user = register.user, monitor = monitor
        )
    }
}