package pimonitor

import bitframe.Application
import bitframe.server.InMemoryDaoProvider
import bitframe.server.modules.authentication.AuthenticationService
import bitframe.server.modules.authentication.DefaultAuthenticationController
import bitframe.server.modules.authentication.DefaultAuthenticationModule
import bitframe.server.modules.authentication.DefaultAuthenticationService
import users.user.Basic
import users.user.Contacts
import users.user.CreateUserParams

fun main() {
    val provider = InMemoryDaoProvider()
    val authModule = DefaultAuthenticationModule(provider)
    val server = Application(authModule, listOf())
    server.start()
}