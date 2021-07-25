package bitframe.authentication

import kotlinx.serialization.Serializable

@Serializable
data class LoginConundrum(
    val user: User,
    val accounts: List<Account>
)