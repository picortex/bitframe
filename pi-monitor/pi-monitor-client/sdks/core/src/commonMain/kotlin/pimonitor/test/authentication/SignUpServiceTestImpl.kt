package pimonitor.test.authentication

import bitframe.authentication.ClientConfiguration
import bitframe.authentication.TestClientConfiguration
import pimonitor.authentication.SignUpService

class SignUpServiceTestImpl(private val configuration: TestClientConfiguration) : SignUpService {
    override val config: ClientConfiguration = configuration
}