package pimonitor.client.picortex

import bitframe.client.ServiceConfigMock
import pimonitor.client.sage.SageDashboardService
import pimonitor.core.picortex.PiCortexDashboardDaodService
import pimonitor.core.picortex.PiCortexDashboardServiceCore
import pimonitor.core.sage.SageDashboardServiceCore

class PiCortexDashboardServiceMock(
    override val config: ServiceConfigMock
) : PiCortexDashboardService(config), PiCortexDashboardServiceCore by PiCortexDashboardDaodService(config)