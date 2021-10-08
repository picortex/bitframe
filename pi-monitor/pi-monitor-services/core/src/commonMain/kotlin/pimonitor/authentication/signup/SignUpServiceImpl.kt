package pimonitor.authentication.signup

import bitframe.authentication.apps.App
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.users.UsersService
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import later.later
import pimonitor.Monitor
import pimonitor.monitors.MonitorDao
import pimonitor.monitors.SignUpParams
import pimonitor.monitors.toRegisterSpaceParams
import pimonitor.monitors.toRegisterUserParams

class SignUpServiceImpl(
    private val dao: MonitorDao,
    private val usersService: UsersService,
    private val config: ServiceConfig
) : SignUpService() {

    private val scope = config.scope
    override fun registerIndividuallyAs(
        person: IndividualRegistrationParams
    ) = usersService.register(person.toRegisterUserParamsNew())

    override fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<LoginConundrum> {
        Monitor("<unset>", business, contacts = listOf(representedBy))
        TODO()
    }

    override fun signUp(params: SignUpParams): Later<SignUpResult> = scope.later {
        validate(params)
        val register = when (params) {
            is SignUpParams.Individual -> usersService.register(params.toRegisterUserParams())
            is SignUpParams.Business -> usersService.registerWithSpace(
                user = params.toRegisterUserParams(), space = params.toRegisterSpaceParams()
            )
        }
        val monitor = dao.create(params).await()
        val conundrum = register.await()
        SignUpResult(
            app = App(config.appId), space = conundrum.spaces.first(), user = conundrum.user, monitor = monitor
        )
    }
}