package pimonitor.api

import bitframe.client.BitframeApiMock
import pimonitor.client.authentication.signup.SignUpServiceMock
import kotlin.jvm.JvmOverloads

class PiMonitorApiMock @JvmOverloads constructor(
    override val config: PiMonitorApiMockConfig = PiMonitorApiMockConfig()
) : PiMonitorApi, BitframeApiMock by BitframeApiMock(config) {
    override val signUp by lazy { SignUpServiceMock(config) }
//    override val monitors by lazy { MonitorsServiceMock(config) }
//    override val businesses by lazy { BusinessesServiceMock(config) }
//    override val portfolio by lazy { PortfolioService(businesses) }
}