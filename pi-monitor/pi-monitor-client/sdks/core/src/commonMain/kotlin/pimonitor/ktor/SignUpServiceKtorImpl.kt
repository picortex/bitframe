package pimonitor.ktor

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.KtorClientConfiguration
import later.Later
import later.later
import pimonitor.IndividualRegistrationParams
import pimonitor.Monitor
import pimonitor.authentication.SignUpService
import pimonitor.toPerson

class SignUpServiceKtorImpl(
    configuration: KtorClientConfiguration
) : SignUpService {
    override val config: ClientConfiguration = configuration
    override fun registerIndividuallyAs(person: IndividualRegistrationParams) = config.scope.later {
        person.toPerson()
    }

    override fun register(business: Monitor.Business, representedBy: Monitor.Person): Later<Monitor> {
        TODO("Not yet implemented")
    }
}