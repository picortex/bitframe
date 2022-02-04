package bitframe.server.testing

import bitframe.Application
import bitframe.ApplicationConfig
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDaoInMemory
import bitframe.authentication.users.UsersDaoInMemoryConfig
import bitframe.daos.MockDaoFactory
import bitframe.server.BitframeService
import bitframe.service.server.config.ServiceConfig
import java.io.File

fun main(vararg args: String) {
    val serviceConfig = ServiceConfig(
        daoFactory = MockDaoFactory()
    )

    val appConfig = ApplicationConfig(
        client = File(args[0]),
        service = BitframeService(serviceConfig)
    )

    val app = Application(appConfig)
    app.start()
}