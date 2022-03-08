package pimonitor.client.sage

import bitframe.client.ServiceConfigMock
import pimonitor.core.sage.SageDashboardDaodService
import pimonitor.core.sage.SageDashboardServiceCore

class SageDashboardServiceMock(
    override val config: ServiceConfigMock
) : SageDashboardService(config), SageDashboardServiceCore by SageDashboardDaodService(config)