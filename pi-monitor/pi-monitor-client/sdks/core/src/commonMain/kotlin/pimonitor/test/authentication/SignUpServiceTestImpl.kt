package pimonitor.test.authentication

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.LoginConundrum
import bitframe.authentication.TestClientConfiguration
import kotlinx.coroutines.delay
import later.Later
import later.later
import pimonitor.IndividualRegistrationParams
import pimonitor.Monitor
import pimonitor.authentication.SignUpService
import pimonitor.toPerson

class SignUpServiceTestImpl(private val configuration: TestClientConfiguration) : SignUpService {
    override val config: ClientConfiguration = configuration

    override fun registerIndividuallyAs(
        person: IndividualRegistrationParams
    ) : Later<LoginConundrum> = config.scope.later {
        delay(configuration.simulationTime.toLong())
        person.toPerson()
        TODO()
    }

    override fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<LoginConundrum> = config.scope.later {
        delay(configuration.simulationTime.toLong())
        Monitor("<unset>", business, contacts = listOf(representedBy))
        TODO()
    }
}