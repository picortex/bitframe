package pimonitor.client.business.operations

import bitframe.client.ServiceConfigMock
import pimonitor.core.business.operations.BusinessOperationsServiceDaod
import pimonitor.core.business.operations.BusinessOperationsServiceCore

class BusinessOperationsServiceMock(
    private val config: ServiceConfigMock
) : BusinessOperationsService(config), BusinessOperationsServiceCore by BusinessOperationsServiceDaod(config)