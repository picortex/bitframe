package pimonitor

import bitframe.daos.MockDaoFactory
import bitframe.daos.MongoDaoFactory
import bitframe.daos.MongoDaoFactoryConfig
import bitframe.service.server.config.ServiceConfig
import java.io.File

fun main(args: Array<String>) {
//    val daoFactory = MongoDaoFactory(
//        config = MongoDaoFactoryConfig(
//            host = "127.0.0.1:27017",
//            username = "root",
//            password = "example",
//            database = "pimonitor-staging"
//        )
//    )

    val daoFactory = MockDaoFactory()
    val config = PiMonitorApplicationConfig(
        config = ServiceConfig(
            daoFactory = daoFactory
        ),
        daoFactory = daoFactory,
        client = File(args[0])
    )
    val server = PiMonitorServer(config)
    server.start()
}