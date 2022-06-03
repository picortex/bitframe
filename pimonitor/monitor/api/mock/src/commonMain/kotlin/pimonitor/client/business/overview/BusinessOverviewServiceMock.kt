package pimonitor.client.business.overview

import pimonitor.client.MonitorApiConfigMock
import pimonitor.core.business.overview.BusinessOverviewServiceCore
import pimonitor.core.business.overview.BusinessOverviewServiceDaod

class BusinessOverviewServiceMock(
    config: MonitorApiConfigMock
) : BusinessOverviewService(config), BusinessOverviewServiceCore by BusinessOverviewServiceDaod(config)