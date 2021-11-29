package bitframe.server.authentication.signup

import bitframe.authentication.users.UsersService
import bitframe.service.config.ServiceConfig
import bitframe.monitors.MonitorDao

interface SignUpServiceConfig : ServiceConfig {
    val monitorsDao: MonitorDao
    val usersService: UsersService
}