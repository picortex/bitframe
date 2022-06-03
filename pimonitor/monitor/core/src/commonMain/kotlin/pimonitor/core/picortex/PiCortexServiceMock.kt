package pimonitor.core.picortex

import pimonitor.core.dashboards.DashboardProvider

class PiCortexServiceMock(
    private val config: PiCortexServiceConfig = PiCortexServiceConfig()
) : PiCortexService {

    override fun offeredTo(
        credentials: PiCortexApiCredentials
    ): DashboardProvider = PiCortexDashboardProviderMock(config)

    override fun toString() = "PiCortexServiceMock"
}