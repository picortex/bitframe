package bitframe.server.modules.authentication

import bitframe.authentication.users.Contacts

data class RegisterUserParams(
    val name: String,
    val contacts: Contacts,
    val password: String
)