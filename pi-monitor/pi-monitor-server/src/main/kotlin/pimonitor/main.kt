package pimonitor

import bitframe.daos.MongoDaoFactory
import bitframe.daos.MongoDaoFactoryConfig
import bitframe.server.bitframeApplication
import bitframe.server.modules.GenericModule
import bitframe.service.server.config.ServiceConfig
import pimonitor.authentication.signup.SignUpController
import pimonitor.authentication.signup.SignUpModule
import pimonitor.server.PiMonitorService
import pimonitor.server.populateTestEntities
import java.io.File

fun main(args: Array<String>) {
    bitframeApplication<PiMonitorService> {
        public = File(args.getOrNull(0) ?: "/default")
        database {
//            MockDaoFactory()
            MongoDaoFactory(
                config = MongoDaoFactoryConfig(
                    host = "127.0.0.1:27017",
                    username = "root",
                    password = "example",
                    database = "pi"
                )
            )
        }

        service { factory ->
            PiMonitorService(ServiceConfig(factory))
        }

        install { ser ->
            SignUpModule(SignUpController(ser.signUp))
        }

        install {
            GenericModule(it.userEmails)
        }

        onStart { populateTestEntities() }
    }.start()
}