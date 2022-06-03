package pimonitor.client.business.operations

import pimonitor.client.MonitorApiConfigMock
import pimonitor.core.business.operations.BusinessOperationsServiceCore
import pimonitor.core.business.operations.BusinessOperationsServiceDaod

class BusinessOperationsServiceMock(
    private val config: MonitorApiConfigMock
) : BusinessOperationsService(config), BusinessOperationsServiceCore by BusinessOperationsServiceDaod(config)