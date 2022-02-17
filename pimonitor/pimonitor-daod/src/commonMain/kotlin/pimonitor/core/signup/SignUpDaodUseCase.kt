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

class SignUpDaodUseCase(
    val config: DaodServiceConfig
) : SignUpUseCase, RegisterUserUseCase by RegisterUserUseCaseImpl(config) {
    private val scope get() = config.scope
    private val businessDao by lazy { config.daoFactory.get<MonitorBusinessBasicInfo>() }

    override fun signUp(rb: RequestBody.UnAuthorized<SignUpParams>) = scope.later {
        val params = rb.data
        val result = register(params.toRegisterUserParams()).await()
        val space = result.spaces.first()
        (params as? SignUpParams.Business)?.let {
            businessDao.create(MonitorBusinessBasicInfo(spaceId = space.uid, owningSpaceId = space.uid))
        }
        SignUpResult(
            app = App(rb.appId), space = space, user = result.user
        )
    }
}