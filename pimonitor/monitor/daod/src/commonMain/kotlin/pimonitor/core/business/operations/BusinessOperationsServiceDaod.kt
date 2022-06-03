package pimonitor.core.business.operations

import bitframe.core.RequestBody
import bitframe.core.get
import bitframe.core.isEqualTo
import later.await
import later.later
import pimonitor.core.business.utils.info.LoadInfoParsedParams
import pimonitor.core.businesses.DASHBOARD_OPERATIONAL
import pimonitor.core.businesses.MonitoredBusinessBasicInfo
import pimonitor.core.configs.MonitorServiceConfigDaod
import pimonitor.core.dashboards.OperationalDifferenceBoard
import pimonitor.core.invites.InfoResults
import pimonitor.core.picortex.PiCortexApiCredentials

open class BusinessOperationsServiceDaod(
    val config: MonitorServiceConfigDaod
) : BusinessOperationsServiceCore {
    private val factory get() = config.daoFactory
    private val monitoredBusinessesDao by lazy { factory.get<MonitoredBusinessBasicInfo>() }
    private val piCortexCredentialsDao by lazy { factory.get<PiCortexApiCredentials>() }
    private val piCortexDashboardProvider by lazy { config.picortex }

    override fun dashboard(rb: RequestBody.Authorized<LoadInfoParsedParams>) = config.scope.later {
        val business = monitoredBusinessesDao.load(rb.data.businessId).await()
        val params = rb.data
        when (business.operationalBoard) {
            DASHBOARD_OPERATIONAL.NONE -> {
                InfoResults.NotShared(
                    business = business,
                    message = "${business.name} has not shared their reports with any dashboard"
                ) as InfoResults<OperationalDifferenceBoard>
            }
            DASHBOARD_OPERATIONAL.PICORTEX -> {
                val cred = piCortexCredentialsDao.all(condition = PiCortexApiCredentials::businessId isEqualTo business.uid).await().last()
                val provider = piCortexDashboardProvider.offeredTo(cred)
                val board = provider.technicalDifferenceDashboardOf(params.start, params.end).await()
                InfoResults.Shared(business, board)
            }
            else -> error("Business is connected to an unknown operational board provider")
        }
    }
}