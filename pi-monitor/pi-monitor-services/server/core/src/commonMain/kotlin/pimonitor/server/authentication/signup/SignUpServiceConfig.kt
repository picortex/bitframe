package pimonitor.server.authentication.signup

import bitframe.authentication.users.UsersService
import bitframe.service.config.ServiceConfig
import pimonitor.monitors.MonitorDao

interface SignUpServiceConfig : ServiceConfig {
    val monitorsDao: MonitorDao
    val usersService: UsersService
}