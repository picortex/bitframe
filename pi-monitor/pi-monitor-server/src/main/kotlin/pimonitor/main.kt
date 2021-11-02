package pimonitor

import bitframe.events.InMemoryEventBus
import bitframe.server.InMemoryDaoProvider
import bitframe.server.modules.authentication.AuthenticationServiceImpl
import bitframe.service.config.ServiceConfig
import cache.MockCache
import java.io.File

fun main(args: Array<String>) {
    val provider = InMemoryDaoProvider() // MySQLProvider, MongoDBProvider
    val config = ServiceConfig("server-app")
    val bus = InMemoryEventBus()
    val cache = MockCache()
    val authModule = AuthenticationServiceImpl(provider, config, cache, bus)
    val client = File(args[0])
    val server = PiMonitorServer(client, cache, authModule)
    server.start()
}