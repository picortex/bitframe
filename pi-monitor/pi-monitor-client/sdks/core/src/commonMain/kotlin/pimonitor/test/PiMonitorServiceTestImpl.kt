package pimonitor.test

import bitframe.BitframeTestClient
import bitframe.authentication.TestClientConfiguration
import pimonitor.PiMonitorService
import pimonitor.authentication.SignUpService
import pimonitor.test.authentication.SignUpServiceTestImpl
import kotlin.js.JsExport

@JsExport
class PiMonitorServiceTestImpl(
    configuration: TestClientConfiguration
) : PiMonitorService(), BitframeTestClient by BitframeTestClient(configuration) {
    override val signUp: SignUpService = SignUpServiceTestImpl(configuration)
}