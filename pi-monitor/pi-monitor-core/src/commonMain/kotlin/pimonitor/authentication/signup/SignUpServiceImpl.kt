package pimonitor.authentication.signup

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.UsersService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import later.Later
import pimonitor.Monitor
import pimonitor.toPerson

class SignUpServiceImpl(
    val usersService: UsersService
) : SignUpService(), CoroutineScope by CoroutineScope(SupervisorJob()) {

    override fun registerIndividuallyAs(
        person: IndividualRegistrationParams
    ): Later<LoginConundrum> {
        return usersService.register(person.toRegisterUserParams())
    }

    override fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<LoginConundrum> {
        Monitor("<unset>", business, contacts = listOf(representedBy))
        TODO()
    }
}