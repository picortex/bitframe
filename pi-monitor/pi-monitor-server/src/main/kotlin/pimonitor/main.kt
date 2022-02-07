package pimonitor

import bitframe.daos.MongoDaoFactory
import bitframe.daos.MongoDaoFactoryConfig
import bitframe.server.bitframeApplication
import bitframe.service.server.config.ServiceConfig
import pimonitor.server.PiMonitorService
import pimonitor.server.populateTestEntities
import java.io.File

fun main(args: Array<String>) {
    bitframeApplication {
        public = File(args.getOrNull(0) ?: "/default")
        database {
//            MockDaoFactory()
            MongoDaoFactory(
                config = MongoDaoFactoryConfig(
                    host = "127.0.0.1:27017",
                    username = "root",
                    password = "example",
                    database = "pimonitor-staging"
                )
            )
        }
        service { factory ->
            PiMonitorService(ServiceConfig(factory))
        }
        onStart { populateTestEntities() }
    }.start()
}