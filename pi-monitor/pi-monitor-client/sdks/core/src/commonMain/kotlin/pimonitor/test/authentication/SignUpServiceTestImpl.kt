package pimonitor.test.authentication

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.TestClientConfiguration
import kotlinx.coroutines.delay
import later.later
import pimonitor.IndividualRegistrationParams
import pimonitor.Monitor
import pimonitor.authentication.SignUpService
import pimonitor.toPerson

class SignUpServiceTestImpl(private val configuration: TestClientConfiguration) : SignUpService {
    override val config: ClientConfiguration = configuration

    override fun registerIndividuallyAs(person: IndividualRegistrationParams) = config.scope.later {
        delay(configuration.simulationTime.toLong())
        person.toPerson()
    }

    override fun register(business: Monitor.Business, representedBy: Monitor.Person) = config.scope.later {
        delay(configuration.simulationTime.toLong())
        Monitor("<unset>", business, contacts = listOf(representedBy))
    }
}