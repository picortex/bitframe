package pimonitor.server.businesses

import pimonitor.evaluation.businesses.BusinessesServiceConfig
import pimonitor.monitored.MonitoredBusinessDao

interface BusinessesServiceConfig : BusinessesServiceConfig {
    val dao: MonitoredBusinessDao
}