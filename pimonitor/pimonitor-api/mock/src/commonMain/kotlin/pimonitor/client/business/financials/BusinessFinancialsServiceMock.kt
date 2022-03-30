package pimonitor.client.business.financials

import bitframe.client.ServiceConfigMock
import pimonitor.core.business.financials.BusinessFinancialsServiceCore
import pimonitor.core.business.financials.BusinessFinancialsServiceDaod

class BusinessFinancialsServiceMock(
    private val config: ServiceConfigMock
) : BusinessFinancialsService(config), BusinessFinancialsServiceCore by BusinessFinancialsServiceDaod(config)