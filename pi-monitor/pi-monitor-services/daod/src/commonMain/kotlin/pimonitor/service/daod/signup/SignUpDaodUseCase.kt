package pimonitor.service.daod.signup

import bitframe.actors.apps.App
import bitframe.actors.users.usecases.RegisterUserUseCase
import bitframe.authentication.service.daod.usecase.RegisterUserUseCaseImpl
import bitframe.daos.get
import bitframe.service.daod.config.DaodServiceConfig
import bitframe.service.requests.RequestBody
import later.await
import later.later
import pimonitor.authentication.signup.*
import pimonitor.businesses.BUSINESS_TYPE
import pimonitor.businesses.Business

class SignUpDaodUseCase(
    val config: DaodServiceConfig
) : SignUpUseCase, RegisterUserUseCase by RegisterUserUseCaseImpl(config) {
    private val scope get() = config.scope
    private val businessDao by lazy { config.daoFactory.get<Business>() }

    override fun signUp(rb: RequestBody.UnAuthorized<SignUpParams>) = scope.later {
        val params = rb.data
        val result = register(params.toRegisterUserParams()).await()
        val space = result.spaces.first()
        (params as? SignUpParams.Business)?.let {
            businessDao.create(Business(spaceId = space.uid, type = BUSINESS_TYPE.MONITOR))
        }
        SignUpResult(
            app = App(rb.appId), space = space, user = result.user
        )
    }
}