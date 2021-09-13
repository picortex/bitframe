package pimonitor

import bitframe.Application
import bitframe.server.InMemoryDaoProvider
import bitframe.server.modules.Module
import bitframe.server.modules.authentication.DefaultAuthenticationModule
import java.io.File

fun main(args: Array<String>) {
    val provider = InMemoryDaoProvider() // MySQLProvider, MongoDBProvider
    val authModule = DefaultAuthenticationModule(provider)
    val client = File(args[0])
    val server = Application(
        client,
        authModule,
        listOf(
            Module<Monitor>(),
            Module<Monitor.Business>("monitor-businesses"),
            Module<Monitored>(),
        )
    )
    server.start()
}