package pimonitor.client.businesses

import pimonitor.client.MonitorApiConfigMock
import pimonitor.core.businesses.BusinessesServiceDaod
import pimonitor.core.businesses.BusinessesServiceCore as CoreBusinessesService

class BusinessesServiceMock(
    internal val config: MonitorApiConfigMock
) : BusinessesService(config), CoreBusinessesService by BusinessesServiceDaod(config)