package bitframe

import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.config.ServiceConfig
import bitframe.authentication.configs.DaoConfig
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDao
import bitframe.authentication.users.UsersDaoInMemory
import kotlin.js.JsExport

@JsExport
open class BitframeTestClientImpl(
    override val config: TestClientConfiguration,
    val daoConfig: DaoConfig = DaoConfig(
        simulationTime = config.simulationTime,
        scope = config.scope
    ),
    usersDao: UsersDao = UsersDaoInMemory(config = daoConfig, users = testUsers(daoConfig)),
    spacesDao: SpacesDao = SpacesDaoInMemory(config = daoConfig, spaces = testSpaces(daoConfig))
) : BitframeTestClient {
    companion object {
        private fun testSpaces(config: DaoConfig) = mutableMapOf(
            "space-01" to Space(
                uid = "space-01",
                name = "Space One",
                photoUrl = null,
                scope = "",
                type = ""
            )
        )

        private fun testUsers(config: DaoConfig) = mutableMapOf(
            "user-1" to User(
                uid = "user-1",
                name = "User One",
                tag = "user01",
                contacts = Contacts.of("user01@test.com"),
                photoUrl = null,
                spaces = listOf(testSpaces(config)["space-01"]!!)
            )
        )
    }

    override val signIn: SignInService = SignInServiceImpl(usersDao, spacesDao, ServiceConfig(config.scope))
}