package pimonitor.client

import bitframe.client.BitframeApiMock
import bitframe.client.BitframeApiMockConfig
import pimonitor.client.signup.SignUpServiceMock
import kotlin.jvm.JvmOverloads

class PiMonitorApiMock @JvmOverloads constructor(
    override val config: BitframeApiMockConfig = BitframeApiMockConfig()
) : PiMonitorApi, BitframeApiMock by BitframeApiMock(config) {
    override val signUp by lazy { SignUpServiceMock(config) }
//    override val monitors by lazy { MonitorsServiceMock(config) }
//    override val businesses by lazy { BusinessesServiceMock(config) }
//    override val portfolio by lazy { PortfolioService(businesses) }
}