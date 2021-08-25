package pimonitor

import bitframe.Application
import bitframe.server.InMemoryDaoProvider
import bitframe.server.modules.Module
import bitframe.server.modules.authentication.DefaultAuthenticationModule

fun main() {
    val provider = InMemoryDaoProvider() // MySQLProvider, MongoDBProvider
    val authModule = DefaultAuthenticationModule(provider)
    val server = Application(
        authModule,
        listOf(
            Module<Monitor>(),
            Module<Monitor.Business>("monitor-businesses"),
            Module<Monitored>(),
        )
    )
    server.start()
}