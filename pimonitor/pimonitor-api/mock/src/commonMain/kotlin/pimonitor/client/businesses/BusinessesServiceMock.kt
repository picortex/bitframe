package pimonitor.client.businesses

import bitframe.client.MockServiceConfig
import pimonitor.core.businesses.BusinessesDaodService
import pimonitor.core.businesses.BusinessesService as CoreBusinessesService

class BusinessesServiceMock(
    override val config: MockServiceConfig
) : BusinessesService(config), CoreBusinessesService by BusinessesDaodService(config)