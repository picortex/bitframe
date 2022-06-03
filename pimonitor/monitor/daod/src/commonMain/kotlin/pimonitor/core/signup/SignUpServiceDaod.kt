package pimonitor.core.signup

import bitframe.core.*
import bitframe.core.users.RegisterUserUseCase
import bitframe.core.users.RegisterUserUseCaseImpl
import later.await
import later.later
import pimonitor.core.businesses.MonitorBusinessBasicInfo
import pimonitor.core.signup.params.*

open class SignUpServiceDaod(
    open val config: ServiceConfigDaod
) : SignUpServiceCore, RegisterUserUseCase by RegisterUserUseCaseImpl(config) {
    private val scope get() = config.scope
    private val businessDao by lazy { config.daoFactory.get<MonitorBusinessBasicInfo>() }

    override fun signUpAsBusiness(rb: RequestBody.UnAuthorized<SignUpBusinessParams>) = scope.later {
        val params = rb.data.toValidatedSignUpParams()
        val result = register(params.toRegisterUserParams()).await()
        val space = result.spaces.first()
        businessDao.create(MonitorBusinessBasicInfo(name = params.businessName, owningSpaceId = space.uid)).await()
        SignUpResult(
            app = App(rb.appId), space = space, user = result.user
        )
    }

    override fun signUpAsIndividual(rb: RequestBody.UnAuthorized<SignUpIndividualParams>) = scope.later {
        val params = rb.data.toValidatedSignUpParams()
        val result = register(params.toRegisterUserParams()).await()
        val space = result.spaces.first()
        SignUpResult(
            app = App(rb.appId), space = space, user = result.user
        )
    }

    fun signUp(rb: RequestBody.UnAuthorized<SignUpRawParams>) = when (val params = rb.data) {
        is SignUpBusinessRawParams -> signUpAsBusiness(rb.map { params.toValidatedSignUpParams() })
        is SignUpIndividualRawParams -> signUpAsIndividual(rb.map { params.toValidatedSignUpParams() })
    }
}