package pimonitor.client.picortex

import bitframe.client.ServiceConfigMock
import pimonitor.core.picortex.PiCortexDashboardDaodService
import pimonitor.core.picortex.PiCortexDashboardServiceCore

class PiCortexDashboardServiceMock(
    override val config: ServiceConfigMock
) : PiCortexDashboardService(config), PiCortexDashboardServiceCore by PiCortexDashboardDaodService(config)