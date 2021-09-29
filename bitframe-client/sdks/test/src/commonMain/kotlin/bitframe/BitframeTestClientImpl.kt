package bitframe

import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.config.ServiceConfig
import bitframe.authentication.configs.DaoConfig
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.*
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
        private fun testSpaces(config: DaoConfig) = (1..3).associate {
            val key = "space-$it"
            key to Space(
                uid = key,
                name = "Space Two",
                photoUrl = null,
                scope = "",
                type = ""
            )
        }.toMutableMap()

        private fun testUsers(config: DaoConfig) = mutableMapOf(
            "user-1" to User(
                uid = "user-1",
                name = "User One",
                tag = "user01",
                contacts = Contacts.of("user01@test.com"),
                photoUrl = null,
                spaces = listOf(testSpaces(config)["space-1"]!!)
            ),
            "user-2" to User(
                uid = "user-2",
                name = "User Two",
                tag = "user02",
                contacts = Contacts.of("user02@test.com"),
                photoUrl = null,
                spaces = testSpaces(config).values.toList().slice(1..2)
            )
        )
    }

    override val signIn: SignInService = SignInServiceImpl(usersDao, spacesDao, ServiceConfig(config.scope))
    override val users: UsersService = UsersServiceImpl(usersDao, spacesDao)
}