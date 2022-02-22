package pimonitor.server

import bitframe.core.MockDaoFactory
import bitframe.server.MongoDaoFactory
import bitframe.server.MongoDaoFactoryConfig
import bitframe.server.ServiceConfig
import bitframe.server.bitframeApplication
import pimonitor.server.businesses.BusinessController
import pimonitor.server.businesses.BusinessModule
import pimonitor.server.profile.ProfileController
import pimonitor.server.profile.ProfileModule
import pimonitor.server.signup.SignUpController
import pimonitor.server.signup.SignUpModule
import java.io.File

fun main(args: Array<String>) {
    bitframeApplication<PiMonitorService> {
        public = File(args.getOrNull(0) ?: "/default")
        database {
            MockDaoFactory()
            MongoDaoFactory(
                config = MongoDaoFactoryConfig(
//                    host = "127.0.0.1:27017",
                    host = "database:27017",
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
            SignUpModule(SignUpController(ser.signup))
        }
        install { ser ->
            BusinessModule(BusinessController(ser.businesses))
        }
        install { ser ->
            ProfileModule(ProfileController(ser.profile))
        }

        onStart { populateTestEntities() }
    }.start()
}