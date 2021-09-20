package pimonitor.test.authentication

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.TestClientConfiguration
import kotlinx.coroutines.delay
import later.Later
import later.later
import pimonitor.Monitor
import pimonitor.authentication.SignUpService

class SignUpServiceTestImpl(private val configuration: TestClientConfiguration) : SignUpService {
    override val config: ClientConfiguration = configuration

    override fun registerIndividuallyAs(person: Monitor.Person) = config.scope.later {
        delay(configuration.simulationTime.toLong())
        person
    }

    override fun register(business: Monitor.Business, representedBy: Monitor.Person) = config.scope.later {
        delay(configuration.simulationTime.toLong())
        Monitor("<unset>", business, contacts = listOf(representedBy))
    }
}