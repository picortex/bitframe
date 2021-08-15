package pimonitor

import bitframe.Application
import bitframe.server.modules.authentication.DefaultAuthenticationController
import bitframe.server.modules.authentication.DefaultAuthenticationModule
import users.user.Basic
import users.user.Contacts
import users.user.CreateUserParams

fun main() {
    val admin = CreateUserParams(
        name = "System Admin",
        contacts = Contacts.None,
        credentials = Basic("admin", "admin")
    )
    val controller = DefaultAuthenticationController(service)
    val authModule = DefaultAuthenticationModule(admin, controller)
    val server = Application(authModule, listOf())
    server.start()
}