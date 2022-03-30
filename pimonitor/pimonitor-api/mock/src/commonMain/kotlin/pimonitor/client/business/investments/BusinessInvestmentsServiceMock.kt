package pimonitor.client.business.investments

import bitframe.client.ServiceConfigMock
import pimonitor.core.business.investments.BusinessInvestmentsServiceDaod
import pimonitor.core.business.investments.BusinessInvestmentsServiceCore

open class BusinessInvestmentsServiceMock(
    private val config: ServiceConfigMock
) : BusinessInvestmentsService(config), BusinessInvestmentsServiceCore by BusinessInvestmentsServiceDaod(config)