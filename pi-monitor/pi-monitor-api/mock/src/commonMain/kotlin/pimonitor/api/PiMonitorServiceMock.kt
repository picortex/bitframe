package pimonitor.api

import bitframe.api.BitframeServiceMock
import pimonitor.client.BusinessesServiceMock
import pimonitor.client.MonitorsServiceMock
import pimonitor.client.SignUpServiceMock
import pimonitor.portfolio.PortfolioService
import kotlin.jvm.JvmOverloads

class PiMonitorServiceMock @JvmOverloads constructor(
    override val config: PiMonitorServiceMockConfig = PiMonitorServiceMockConfig()
) : PiMonitorService, BitframeServiceMock by BitframeServiceMock(config) {
    override val signUp by lazy { SignUpServiceMock(config) }
    override val monitors by lazy { MonitorsServiceMock(config) }
    override val businesses by lazy { BusinessesServiceMock(config) }
    override val portfolio by lazy { PortfolioService(businesses) }
}