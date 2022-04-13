package pimonitor.client.business.investments

import bitframe.client.ServiceConfigMock
import pimonitor.core.investments.BusinessInvestmentsServiceDaod
import pimonitor.core.investments.BusinessInvestmentsServiceCore

open class BusinessInvestmentsServiceMock(
    private val config: ServiceConfigMock
) : BusinessInvestmentsService(config), BusinessInvestmentsServiceCore by BusinessInvestmentsServiceDaod(config)