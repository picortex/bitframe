package bitframe.authentication

class UserDatabase {
    fun accountsFor(username: String): List<Account> {
        return when (username) {
            "user1" -> listOf(
                Account("account-1-1", "Account 1-1")
            )
            "user2" -> listOf(
                Account("account-2-1", "Account 2-1"),
                Account("account-2-2", "Account 2-2")
            )
            else -> listOf()
        }
    }

    fun credentialsFor(username: String): UserCredentials? {
        return when (username) {
            "user1" -> UserCredentials(username, "pass1")
            "user2" -> UserCredentials(username, "pass2")
            else -> null
        }
    }
}