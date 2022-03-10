package pimonitor.client.businesses

import bitframe.client.ServiceConfigMock
import pimonitor.core.businesses.BusinessesDaodService
import pimonitor.core.businesses.BusinessesServiceCore as CoreBusinessesService

class BusinessesServiceMock(
    override val config: ServiceConfigMock
) : BusinessesService(config), CoreBusinessesService by BusinessesDaodService(config)