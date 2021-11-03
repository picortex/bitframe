package pimonitor.client.authentication.signup

import bitframe.authentication.users.UsersService
import bitframe.service.client.config.ServiceConfig
import pimonitor.authentication.signup.SignUpServiceConfig
import pimonitor.monitors.MonitorDao

interface SignUpServiceConfig : SignUpServiceConfig, ServiceConfig {
    val dao: MonitorDao
    val usersService: UsersService
}