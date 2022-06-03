package pimonitor.client.business.financials

import pimonitor.client.MonitorApiConfigMock
import pimonitor.core.business.financials.BusinessFinancialsServiceCore
import pimonitor.core.business.financials.BusinessFinancialsServiceDaod

class BusinessFinancialsServiceMock(
    private val config: MonitorApiConfigMock
) : BusinessFinancialsService(config), BusinessFinancialsServiceCore by BusinessFinancialsServiceDaod(config)