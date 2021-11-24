package bitframe.server.businesses

import bitframe.service.config.ServiceConfig
import bitframe.monitored.MonitoredBusinessesDao

interface BusinessesServiceConfig : ServiceConfig {
    val businessesDao: MonitoredBusinessesDao
}