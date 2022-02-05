package pimonitor

import bitframe.daos.MongoDaoFactory
import bitframe.daos.MongoDaoFactoryConfig
import bitframe.service.server.config.ServiceConfig
import java.io.File

fun main(args: Array<String>) {
    val config = PiMonitorApplicationConfig(
        config = ServiceConfig(
            daoFactory = MongoDaoFactory(
                config = MongoDaoFactoryConfig(
                    host = "127.0.0.1:27017",
                    username = "root",
                    password = "example",
                    database = "pimonitor-staging"
                )
            )
        ),
        client = File(args[0])
    )
    val server = PiMonitorServer(config)
    server.start()
}