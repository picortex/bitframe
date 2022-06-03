package pimonitor.core.picortex

import pimonitor.core.dashboards.DashboardProvider

internal class PiCortexServiceImpl(
    val config: PiCortexServiceConfig = PiCortexServiceConfig()
) : PiCortexService {
    override fun offeredTo(
        credentials: PiCortexApiCredentials
    ): DashboardProvider = PiCortexDashboardProviderImpl(credentials, config)

    override fun toString() = "PiCortexService(host=${config.environment.domain})"
}