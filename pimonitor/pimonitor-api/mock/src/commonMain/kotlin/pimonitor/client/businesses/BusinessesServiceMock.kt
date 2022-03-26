package pimonitor.client.businesses

import bitframe.client.ServiceConfigMock
import pimonitor.core.businesses.BusinessesServiceDaod
import pimonitor.core.businesses.BusinessesServiceCore as CoreBusinessesService

class BusinessesServiceMock(
    config: ServiceConfigMock
) : BusinessesService(config), CoreBusinessesService by BusinessesServiceDaod(config)