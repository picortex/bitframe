package pimonitor.server.monitors

import bitframe.service.config.ServiceConfig
import pimonitor.monitors.MonitorDao

interface MonitorsServiceConfig : ServiceConfig {
    val dao: MonitorDao
}