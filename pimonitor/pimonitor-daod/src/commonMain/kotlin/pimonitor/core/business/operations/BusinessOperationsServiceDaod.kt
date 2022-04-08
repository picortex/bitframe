package pimonitor.core.business.operations

import bitframe.core.RequestBody
import bitframe.core.ServiceConfigDaod
import bitframe.core.get
import bitframe.core.isEqualTo
import later.await
import later.later
import pimonitor.core.business.utils.info.LoadInfoParsedParams
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.dashboards.OperationalDifferenceBoard
import pimonitor.core.invites.InfoResults
import pimonitor.core.picortex.PiCortexApiCredentials
import pimonitor.core.picortex.PiCortexDashboardProvider
import pimonitor.core.picortex.PiCortexDashboardProviderConfig

open class BusinessOperationsServiceDaod(
    val config: ServiceConfigDaod
) : BusinessOperationsServiceCore {
    private val factory get() = config.daoFactory
    private val monitoredBusinessesDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val piCortexCredentialsDao by lazy { factory.get<PiCortexApiCredentials>() }
    private val piCortexDashboardProvider by lazy {
        val cfg = PiCortexDashboardProviderConfig(
            json = config.json, scope = config.scope, environment = PiCortexDashboardProviderConfig.Environment.Staging
        )
        PiCortexDashboardProvider(cfg)
    }

    override fun dashboard(rb: RequestBody.Authorized<LoadInfoParsedParams>) = config.scope.later {
        val business = monitoredBusinessesDao.load(rb.data.businessId).await()
        val params = rb.data
        when (business.operationalBoard) {
            DASHBOARD_OPERATIONAL.NONE -> {
                InfoResults.NotShared("${business.name} has not shared their reports with any dashboard") as InfoResults<OperationalDifferenceBoard>
            }
            DASHBOARD_OPERATIONAL.PICORTEX -> {
                val cred = piCortexCredentialsDao.all(condition = PiCortexApiCredentials::businessId isEqualTo business.uid).await().last()
                val board = piCortexDashboardProvider.technicalDifferenceDashboardOf(cred, params.start, params.end).await()
                InfoResults.Shared(board)
            }
            else -> error("Business is connected to an unknown operational board provider")
        }
    }
}