package pimonitor.authentication.signup

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.users.UsersService
import bitframe.service.config.ServiceConfig
import later.Later
import pimonitor.Monitor
import kotlin.jvm.JvmOverloads

class SignUpServiceImpl @JvmOverloads constructor(
    private val usersService: UsersService,
    val config: ServiceConfig
) : SignUpService() {

    override fun registerIndividuallyAs(
        person: IndividualRegistrationParams
    ) = usersService.register(person.toRegisterUserParamsNew())

    override fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<LoginConundrum> {
        Monitor("<unset>", business, contacts = listOf(representedBy))
        TODO()
    }

    override fun signUp(params: SignUpParams): Later<SignUpResult> {
        TODO("Not yet implemented")
    }
}