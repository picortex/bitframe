package bitframe.server.monitors

import bitframe.service.config.ServiceConfig
import bitframe.monitors.MonitorDao

interface MonitorsServiceConfig : ServiceConfig {
    val monitorsDao: MonitorDao
}