package pimonitor.server.businesses

import bitframe.service.config.ServiceConfig
import mailer.Mailer
import pimonitor.monitored.MonitoredBusinessesDao

interface BusinessesServiceConfig : ServiceConfig {
    val businessesDao: MonitoredBusinessesDao
}