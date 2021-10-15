package pimonitor

import bitframe.events.InMemoryEventBus
import bitframe.server.InMemoryDaoProvider
import bitframe.server.modules.authentication.AuthenticationServiceImpl
import bitframe.service.config.ServiceConfig
import java.io.File

fun main(args: Array<String>) {
    val provider = InMemoryDaoProvider() // MySQLProvider, MongoDBProvider
    val config = ServiceConfig("server-app")
    val bus = InMemoryEventBus()
    val authModule = AuthenticationServiceImpl(provider, config, bus)
    val client = File(args[0])
    val server = PiMonitorServer(client, authModule)
    server.start()
}