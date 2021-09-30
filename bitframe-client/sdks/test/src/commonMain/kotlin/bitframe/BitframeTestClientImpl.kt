package bitframe

import bitframe.authentication.TestClientConfiguration
import bitframe.authentication.signin.SignInService
import bitframe.authentication.signin.SignInServiceImpl
import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.*

open class BitframeTestClientImpl(
    private val config: TestClientConfiguration,
    usersDao: UsersDao = UsersDaoInMemory(config = config.toDaoConfig(), users = testUsers()),
    spacesDao: SpacesDao = SpacesDaoInMemory(config = config.toDaoConfig(), spaces = testSpaces())
) : BitframeTestClient {
    companion object {
        private fun testSpaces() = (1..3).associate {
            val key = "space-$it"
            key to Space(
                uid = key,
                name = "Space Two",
                photoUrl = null,
                scope = "",
                type = ""
            )
        }.toMutableMap()

        private fun testUsers() = mutableMapOf(
            "user-1" to User(
                uid = "user-1",
                name = "User One",
                tag = "user01",
                contacts = Contacts.of("user01@test.com"),
                photoUrl = null,
                spaces = listOf(testSpaces()["space-1"]!!)
            ),
            "user-2" to User(
                uid = "user-2",
                name = "User Two",
                tag = "user02",
                contacts = Contacts.of("user02@test.com"),
                photoUrl = null,
                spaces = testSpaces().values.toList().slice(1..2)
            )
        )
    }

    override val signIn: SignInService = SignInServiceImpl(usersDao, spacesDao, config.toServiceConfig())
    override val users: UsersService = UsersServiceImpl(usersDao, spacesDao, config.toServiceConfig())
}