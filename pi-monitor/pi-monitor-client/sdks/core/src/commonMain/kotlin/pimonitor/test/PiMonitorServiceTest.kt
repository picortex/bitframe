package pimonitor.test

import bitframe.BitframeTestClient
import bitframe.authentication.TestClientConfiguration
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.SignUpService
import pimonitor.authentication.signup.SignUpServiceImpl
import pimonitor.evaulation.business.BusinessService
import pimonitor.test.evaluation.BusinessServiceTestImpl

class PiMonitorServiceTest(
    private val config: TestClientConfiguration
) : PiMonitorService(), BitframeTestClient by BitframeTestClient(config) {
    override val signUp: SignUpService = SignUpServiceImpl(users, config)
    override val business: BusinessService = BusinessServiceTestImpl(config)
}