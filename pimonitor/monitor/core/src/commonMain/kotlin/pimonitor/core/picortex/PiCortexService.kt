package pimonitor.core.picortex

import pimonitor.core.dashboards.DashboardProvider
import kotlin.time.ExperimentalTime

interface PiCortexService {
    fun offeredTo(credentials: PiCortexApiCredentials): DashboardProvider

    companion object {
        operator fun invoke(
            config: PiCortexServiceConfig = PiCortexServiceConfig()
        ): PiCortexService = PiCortexServiceImpl(config)
    }
}