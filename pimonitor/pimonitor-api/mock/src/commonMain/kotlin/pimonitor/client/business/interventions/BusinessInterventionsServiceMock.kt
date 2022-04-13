package pimonitor.client.business.interventions

import bitframe.client.ServiceConfigMock
import pimonitor.core.business.interventions.BusinessInterventionsServiceCore
import pimonitor.core.business.interventions.BusinessInterventionsServiceDaod

open class BusinessInterventionsServiceMock(
    private val config: ServiceConfigMock
) : BusinessInterventionService(config), BusinessInterventionsServiceCore by BusinessInterventionsServiceDaod(config)