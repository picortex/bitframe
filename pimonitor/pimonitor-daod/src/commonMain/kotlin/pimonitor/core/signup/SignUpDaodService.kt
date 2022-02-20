package pimonitor.core.signup

import bitframe.core.App
import bitframe.core.DaodServiceConfig
import bitframe.core.RequestBody
import bitframe.core.get
import bitframe.core.users.RegisterUserUseCase
import bitframe.core.users.RegisterUserUseCaseImpl
import later.await
import later.later
import pimonitor.core.businesses.MonitorBusinessBasicInfo
import pimonitor.core.signup.params.BusinessSignUpRawParams
import pimonitor.core.signup.params.IndividualSignUpRawParams

open class SignUpDaodService(
    open val config: DaodServiceConfig
) : SignUpService, RegisterUserUseCase by RegisterUserUseCaseImpl(config) {
    private val scope get() = config.scope
    private val businessDao by lazy { config.daoFactory.get<MonitorBusinessBasicInfo>() }

    override fun signUpAsBusiness(rb: RequestBody.UnAuthorized<BusinessSignUpRawParams>) = scope.later {
        val params = rb.data.toBusinessSignUpParams()
        val result = register(params.toRegisterUserParams()).await()
        val space = result.spaces.first()
        businessDao.create(MonitorBusinessBasicInfo(spaceId = space.uid, owningSpaceId = space.uid))
        SignUpResult(
            app = App(rb.appId), space = space, user = result.user
        )
    }

    override fun signUpAsIndividual(rb: RequestBody.UnAuthorized<IndividualSignUpRawParams>) = scope.later {
        val params = rb.data.toIndividualSignUpParams()
        val result = register(params.toRegisterUserParams()).await()
        val space = result.spaces.first()
        SignUpResult(
            app = App(rb.appId), space = space, user = result.user
        )
    }
}