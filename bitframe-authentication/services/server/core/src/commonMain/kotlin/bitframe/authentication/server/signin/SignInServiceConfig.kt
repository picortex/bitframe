package bitframe.authentication.server.signin

import bitframe.authentication.users.UsersDao
import bitframe.service.config.ServiceConfig

interface SignInServiceConfig : ServiceConfig {
    val usersDao: UsersDao
}