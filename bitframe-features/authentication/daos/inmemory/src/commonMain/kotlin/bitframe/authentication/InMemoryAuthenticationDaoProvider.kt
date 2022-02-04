package bitframe.authentication

import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDao
import bitframe.authentication.users.UsersDaoInMemory
import kotlinx.collections.interoperable.listOf

class InMemoryAuthenticationDaoProvider(
    val config: InMemoryAuthenticationDaoProviderConfig = InMemoryAuthenticationDaoProviderConfig(),
) : AuthenticationDaoProvider {
    override val users: UsersDao = UsersDaoInMemory(config)
    override val spaces: SpacesDao = SpacesDaoInMemory(config)

    companion object {
        fun testAccounts() = mutableMapOf(
            "space-1" to Space(
                uid = "space-1",
                name = "Test Space 1",
                photoUrl = null,
                scope = "",
                type = ""
            )
        )

        fun testUsers() = mutableMapOf(
            "user-1" to User(
                uid = "user-1",
                name = "Test User",
                tag = "testuser",
                contacts = Contacts("user1@test.com"),
                photoUrl = null,
                spaces = listOf(
                    testAccounts()["space-1"]!!
                )
            )
        )
    }
}