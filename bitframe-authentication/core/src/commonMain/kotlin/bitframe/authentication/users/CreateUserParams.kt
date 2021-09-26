package bitframe.authentication.users

import bitframe.authentication.signin.Credentials
import kotlinx.serialization.Serializable

@Serializable
class CreateUserParams(
    val name: String,
    val contacts: Contacts,
    val credentials: Credentials
)