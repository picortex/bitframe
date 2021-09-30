package pimonitor.authentication.signup

import bitframe.authentication.config.ServiceConfig
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.UsersService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import later.Later
import pimonitor.Monitor
import pimonitor.toPerson
import kotlin.jvm.JvmOverloads

class SignUpServiceImpl @JvmOverloads constructor(
    val usersService: UsersService,
    val config: ServiceConfig = ServiceConfig.DEFAULT
) : SignUpService() {

    override fun registerIndividuallyAs(
        person: IndividualRegistrationParams
    ) = usersService.register(person.toRegisterUserParams())

    override fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<LoginConundrum> {
        Monitor("<unset>", business, contacts = listOf(representedBy))
        TODO()
    }
}