package pimonitor.client.business.overview

import bitframe.client.ServiceConfigMock
import pimonitor.core.business.overview.BusinessOverviewServiceCore
import pimonitor.core.business.overview.BusinessOverviewServiceDaod

class BusinessOverviewServiceMock(
    config: ServiceConfigMock
) : BusinessOverviewService(config), BusinessOverviewServiceCore by BusinessOverviewServiceDaod(config)