package pimonitor.service.daod.signup

import bitframe.actors.apps.App
import bitframe.actors.users.usecases.RegisterUserUseCase
import bitframe.authentication.service.daod.usecase.RegisterUserUseCaseImpl
import bitframe.daos.CompoundDao
import bitframe.daos.get
import bitframe.service.daod.config.DaodServiceConfig
import bitframe.service.requests.RequestBody
import later.Later
import later.await
import later.later
import pimonitor.authentication.signup.*
import pimonitor.monitors.CooperateMonitor
import pimonitor.monitors.IndividualMonitor

class SignUpDaodUseCase(
    val config: DaodServiceConfig
) : SignUpUseCase, RegisterUserUseCase by RegisterUserUseCaseImpl(config) {
    private val scope get() = config.scope
    val monitorsDao by lazy {
        CompoundDao(
            config.daoFactory.get<IndividualMonitor>(),
            config.daoFactory.get<CooperateMonitor>()
        )
    }

    override fun signUp(rb: RequestBody.UnAuthorized<SignUpParams>) = scope.later {
        val params = rb.data
        val result = register(params.toRegisterUserParams()).await()
        val ref = result.user.ref()
        val monitor = monitorsDao.create(params.toMonitor("", ref)).await()
        SignUpResult(
            app = App(rb.appId), space = result.spaces.first(), user = result.user, monitor = monitor
        )
    }
}