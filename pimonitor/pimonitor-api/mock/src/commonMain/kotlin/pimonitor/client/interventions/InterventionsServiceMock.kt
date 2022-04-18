package pimonitor.client.interventions

import bitframe.client.ServiceConfigMock
import pimonitor.core.interventions.InterventionsServiceCore

open class InterventionsServiceMock(
    private val config: ServiceConfigMock
) : InterventionService(config), InterventionsServiceCore by InterventionsServiceMock(config)