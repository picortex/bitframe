package pimonitor

import pimonitor.data.InMemoryPiMonitorDaoProvider
import java.io.File

fun main(args: Array<String>) {
    val config = PiMonitorApplicationConfig(
        daoProvider = InMemoryPiMonitorDaoProvider(), // InMemoryDaoProvider(), // MySQLProvider, MongoDBProvider
        client = File(args[0])
    )
    val server = PiMonitorServer(config)
    server.start()
}