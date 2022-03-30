package pimonitor.client.business.interventions

import bitframe.client.ServiceConfigMock
import pimonitor.client.business.investments.BusinessInvestmentsService
import pimonitor.core.business.investments.BusinessInvestmentsServiceDaod
import pimonitor.core.business.investments.BusinessInvestmentsServiceCore

open class BusinessInterventionsServiceMock(
    private val config: ServiceConfigMock
) : BusinessInvestmentsService(config), BusinessInvestmentsServiceCore by BusinessInvestmentsServiceDaod(config)