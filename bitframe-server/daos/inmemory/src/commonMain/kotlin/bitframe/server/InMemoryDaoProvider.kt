package bitframe.server

import bitframe.authentication.spaces.Space
import bitframe.authentication.spaces.SpacesDao
import bitframe.authentication.spaces.SpacesDaoInMemory
import bitframe.authentication.users.Contacts
import bitframe.authentication.users.User
import bitframe.authentication.users.UsersDao
import bitframe.authentication.users.UsersDaoInMemory
import bitframe.server.data.DAOProvider
import kotlin.jvm.JvmOverloads

class InMemoryDaoProvider @JvmOverloads constructor(
    override val users: UsersDao = UsersDaoInMemory(testUsers()),
    override val spaces: SpacesDao = SpacesDaoInMemory(testAccounts())
) : DAOProvider {
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