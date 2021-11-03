package pimonitor.client.evaluation.businesses

import pimonitor.client.monitors.MonitorsService
import pimonitor.evaluation.businesses.BusinessesServiceConfig

interface BusinessesServiceConfig : BusinessesServiceConfig {
    override val monitorsService: MonitorsService
}