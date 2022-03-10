package pimonitor.core.signup

import bitframe.core.*
import bitframe.core.users.RegisterUserUseCase
import bitframe.core.users.RegisterUserUseCaseImpl
import later.await
import later.later
import pimonitor.core.businesses.MonitorBusinessBasicInfo
import pimonitor.core.signup.params.*

open class SignUpDaodService(
    open val config: ServiceConfigDaod
) : SignUpService, RegisterUserUseCase by RegisterUserUseCaseImpl(config) {
    private val scope get() = config.scope
    private val businessDao by lazy { config.daoFactory.get<MonitorBusinessBasicInfo>() }

    override fun signUpAsBusiness(rb: RequestBody.UnAuthorized<BusinessSignUpParams>) = scope.later {
        val params = rb.data.toBusinessSignUpParams()
        val result = register(params.toRegisterUserParams()).await()
        val space = result.spaces.first()
        businessDao.create(MonitorBusinessBasicInfo(name = params.businessName, owningSpaceId = space.uid)).await()
        SignUpResult(
            app = App(rb.appId), space = space, user = result.user
        )
    }

    override fun signUpAsIndividual(rb: RequestBody.UnAuthorized<IndividualSignUpParams>) = scope.later {
        val params = rb.data.toIndividualSignUpParams()
        val result = register(params.toRegisterUserParams()).await()
        val space = result.spaces.first()
        SignUpResult(
            app = App(rb.appId), space = space, user = result.user
        )
    }

    fun signUp(rb: RequestBody.UnAuthorized<SignUpRawParams>) = when (val params = rb.data) {
        is BusinessSignUpRawParams -> signUpAsBusiness(rb.map { params.toBusinessSignUpParams() })
        is IndividualSignUpRawParams -> signUpAsIndividual(rb.map { params.toIndividualSignUpParams() })
    }
}