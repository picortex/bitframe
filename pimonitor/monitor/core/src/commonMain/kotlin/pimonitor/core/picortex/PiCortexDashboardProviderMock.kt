package pimonitor.core.picortex

import datetime.Date
import kotlinx.collections.interoperable.emptyList
import later.Later
import later.later
import pimonitor.core.dashboards.DashboardProvider
import pimonitor.core.dashboards.OperationalDashboard
import pimonitor.core.dashboards.OperationalDifferenceBoard

internal class PiCortexDashboardProviderMock(
    private val config: PiCortexServiceConfig
) : DashboardProvider {
    override fun technicalDashboardOf(start: Date, end: Date): Later<OperationalDashboard> = config.scope.later {
        OperationalDashboard(emptyList(), emptyList(), emptyList())
    }

    override fun technicalDifferenceDashboardOf(start: Date, end: Date): Later<OperationalDifferenceBoard> = config.scope.later {
        OperationalDifferenceBoard(emptyList(), emptyList(), emptyList())
    }
}