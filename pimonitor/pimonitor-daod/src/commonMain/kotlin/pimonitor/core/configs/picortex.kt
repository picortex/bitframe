package pimonitor.core.configs

import bitframe.core.ServiceConfigDaod
import io.ktor.client.*
import io.ktor.client.plugins.*
import pimonitor.core.picortex.PiCortexDashboardProvider
import pimonitor.core.picortex.PiCortexDashboardProviderConfig

private val ServiceConfigDaod.PICORTEX_ENVIRONMENT get() = PiCortexDashboardProviderConfig.Environment.Production

private val ServiceConfigDaod.piCortexDashboardProviderConfig
    get() = PiCortexDashboardProviderConfig(
        json = json, scope = scope, environment = PICORTEX_ENVIRONMENT, client = HttpClient {
            install(HttpTimeout) { requestTimeoutMillis = 60000L }
        }
    )

internal val ServiceConfigDaod.piCortexDashboard get() = PiCortexDashboardProvider(piCortexDashboardProviderConfig)