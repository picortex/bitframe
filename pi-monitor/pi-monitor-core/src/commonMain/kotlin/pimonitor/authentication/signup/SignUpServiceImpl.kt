package pimonitor.authentication.signup

import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.users.Contacts
import kotlinx.coroutines.delay
import later.Later
import pimonitor.Monitor
import pimonitor.toPerson

class SignUpServiceImpl(
    val usersService: UsersService
) : SignUpService() {
    override fun registerIndividuallyAs(
        person: IndividualRegistrationParams
    ): Later<LoginConundrum> = config.scope.later {
        val user = RegisterUserParams(
            name = params.name ?: throw RuntimeException("Name must not be null"),
            contacts = Contacts.of(params.email ?: throw RuntimeException("Name must not be null")),
            password = params.password ?: throw RuntimeException("Password must not be null")
        )
        delay(config.simulationTime.toLong())
        person.toPerson()
        TODO()
    }

    override fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<LoginConundrum> = config.scope.later {
        delay(config.simulationTime.toLong())
        Monitor("<unset>", business, contacts = listOf(representedBy))
        TODO()
    }
}