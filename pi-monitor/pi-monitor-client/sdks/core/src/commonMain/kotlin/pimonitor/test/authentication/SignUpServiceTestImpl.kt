package pimonitor.test.authentication

import bitframe.MiniService
import bitframe.authentication.ClientConfiguration
import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.users.UsersService
import kotlinx.coroutines.delay
import later.Later
import later.later
import pimonitor.authentication.signup.IndividualRegistrationParams
import pimonitor.Monitor
import pimonitor.authentication.signup.SignUpService
import pimonitor.toPerson

class SignUpServiceTestImpl(
    override val config: TestClientConfiguration,
    val usersService: UsersService
) : SignUpService(), MiniService {

    override fun registerIndividuallyAs(
        person: IndividualRegistrationParams
    ): Later<LoginConundrum> = config.scope.later {
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