package pimonitor.test

import bitframe.BitframeTestClient
import bitframe.authentication.TestClientConfiguration
import pimonitor.PiMonitorService
import pimonitor.authentication.signup.SignUpService
import pimonitor.authentication.signup.SignUpServiceImpl
import pimonitor.evaulation.business.BusinessService
import pimonitor.test.evaluation.BusinessServiceTestImpl

class PiMonitorServiceTest(
    configuration: TestClientConfiguration
) : PiMonitorService(), BitframeTestClient by BitframeTestClient(configuration) {
    override val signUp: SignUpService = SignUpServiceImpl(users)
    override val business: BusinessService = BusinessServiceTestImpl(configuration)
}