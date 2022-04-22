package pimonitor.client.interventions

import bitframe.client.ServiceConfigMock
import pimonitor.core.interventions.InterventionsServiceCore
import pimonitor.core.interventions.InterventionsServiceDaod

open class InterventionsServiceMock(
    private val config: ServiceConfigMock
) : InterventionService(config), InterventionsServiceCore by InterventionsServiceDaod(config)