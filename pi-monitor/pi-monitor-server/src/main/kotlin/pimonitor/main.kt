package pimonitor

import bitframe.daos.MockDaoFactory
import bitframe.service.server.config.ServiceConfig
import java.io.File

fun main(args: Array<String>) {
    val config = PiMonitorApplicationConfig(
        config = ServiceConfig(
            daoFactory = MockDaoFactory()
        ),
        client = File(args[0])
    )
    val server = PiMonitorServer(config)
    server.start()
}