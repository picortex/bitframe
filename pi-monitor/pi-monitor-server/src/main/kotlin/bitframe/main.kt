package bitframe

import bitframe.data.InMemoryPiMonitorDaoProvider
import java.io.File

fun main(args: Array<String>) {
    val config = PiMonitorApplicationConfig(
        provider = InMemoryPiMonitorDaoProvider(),
        client = File(args[0])
    )
    val server = PiMonitorServer(config)
    server.start()
}