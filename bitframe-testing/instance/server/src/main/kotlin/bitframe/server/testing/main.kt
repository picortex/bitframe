package bitframe.server.testing

import bitframe.Application
import bitframe.ApplicationConfig
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDaoInMemory
import bitframe.authentication.users.UsersDaoInMemoryConfig
import java.io.File

fun main(vararg args: String) {

    val serviceConfig = BitframeTestServerServiceConfig(
        spacesDao = SpacesDaoInMemory(TestSpacesDaoConfig()),
        usersDao = UsersDaoInMemory(TestUsersDaoConfig())
    )

    val appConfig = ApplicationConfig(
        client = File(args[0]),
        service = BitframeTestServerService(serviceConfig)
    )

    val app = Application(appConfig)
    app.start()
}