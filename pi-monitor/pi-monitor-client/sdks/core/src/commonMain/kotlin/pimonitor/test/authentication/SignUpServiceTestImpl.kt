package pimonitor.test.authentication

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.TestClientConfiguration
import kotlinx.coroutines.delay
import later.later
import pimonitor.Monitor
import pimonitor.authentication.SignUpService

class SignUpServiceTestImpl(private val configuration: TestClientConfiguration) : SignUpService {
    override val config: ClientConfiguration = configuration
    override fun registerWith(business: Monitor.Business,person: Monitor.Person) = config.scope.later {
        delay(configuration.simulationTime.toLong())
        Monitor("<unset>", business, contacts = listOf(person))
    }
}