package pimonitor.client.investments

import bitframe.client.ServiceConfigMock
import pimonitor.core.investments.InvestmentsServiceCore
import pimonitor.core.investments.InvestmentsServiceDaod

class InvestmentsServiceMock(
    val config: ServiceConfigMock
) : InvestmentsService(config), InvestmentsServiceCore by InvestmentsServiceDaod(config)