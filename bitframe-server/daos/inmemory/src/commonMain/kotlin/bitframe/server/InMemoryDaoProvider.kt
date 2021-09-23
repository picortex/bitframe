package bitframe.server

import bitframe.server.data.DAOProvider
import users.account.Account
import users.server.AccountsDao
import users.server.AccountsDaoInMemory
import users.server.UsersDao
import users.server.UsersDaoInMemory
import users.user.Contacts
import users.user.User
import kotlin.jvm.JvmOverloads

class InMemoryDaoProvider @JvmOverloads constructor(
    override val users: UsersDao = UsersDaoInMemory(testUsers()),
    override val accounts: AccountsDao = AccountsDaoInMemory(testAccounts())
) : DAOProvider {
    companion object {
        fun testAccounts() = mutableMapOf(
            "account-1" to Account(
                uid = "account-1",
                name = "Test Account 1",
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
                contacts = Contacts.of("user1@test.com"),
                photoUrl = null,
                accounts = listOf(
                    testAccounts()["account-1"]!!
                )
            )
        )
    }
}