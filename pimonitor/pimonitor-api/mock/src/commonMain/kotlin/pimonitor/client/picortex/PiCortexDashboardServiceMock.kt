package pimonitor.client.picortex

import bitframe.core.ServiceConfigDaod
import pimonitor.core.picortex.PiCortexDashboardDaodService
import pimonitor.core.picortex.PiCortexDashboardServiceCore

class PiCortexDashboardServiceMock(
    val config: ServiceConfigDaod
) : PiCortexDashboardService, PiCortexDashboardServiceCore by PiCortexDashboardDaodService(config)