package pimonitor.test

import bitframe.BitframeTestClient
import bitframe.authentication.TestClientConfiguration
import pimonitor.PiMonitorService
import pimonitor.evaulation.business.BusinessService
import pimonitor.test.authentication.SignUpServiceTestImpl
import pimonitor.test.evaluation.BusinessServiceTestImpl

class PiMonitorServiceTest(
    configuration: TestClientConfiguration
) : PiMonitorService(), BitframeTestClient by BitframeTestClient(configuration) {
    override val signUp: SignUpService = SignUpServiceTestImpl(configuration)
    override val business: BusinessService = BusinessServiceTestImpl(configuration)
}