package unit

import bitframe.authentication.UserCredentials
import bitframe.authentication.UserDatabase
import expect.expect
import kotlin.test.Test

class UserDatabaseTest {
    private val db = UserDatabase()

    @Test
    fun expect_user_1_to_have_access_to_two_accounts() {
        val accounts = db.accountsFor("user1")
        expect(accounts).toBeOfSize(1)
    }

    @Test
    fun expect_user_2_to_have_access_to_two_accounts() {
        val accounts = db.accountsFor("user2")
        expect(accounts).toBeOfSize(2)
    }

    @Test
    fun expect_user_1_to_have_pass1_as_password() {
        val credentials: UserCredentials? = db.credentialsFor("user1")
        expect(credentials?.password).toBe("pass1")
    }

    @Test
    fun expect_user_2_to_have_pass2_as_password() {
        val credentials: UserCredentials? = db.credentialsFor("user2")
        expect(credentials?.password).toBe("pass2")
    }
}