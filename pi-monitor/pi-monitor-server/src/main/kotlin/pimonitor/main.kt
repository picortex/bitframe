package pimonitor

import bitframe.server.InMemoryDaoProvider
import bitframe.server.modules.authentication.DefaultAuthenticationService
import java.io.File

fun main(args: Array<String>) {
    val provider = InMemoryDaoProvider() // MySQLProvider, MongoDBProvider
    val authModule = DefaultAuthenticationService(provider)
    val client = File(args[0])
    val server = PiMonitorServer(client, authModule)
    server.start()
}