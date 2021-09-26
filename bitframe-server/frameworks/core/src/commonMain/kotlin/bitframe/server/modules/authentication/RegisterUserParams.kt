package bitframe.server.modules.authentication

import users.user.Contacts

data class RegisterUserParams(
    val name: String,
    val contacts: Contacts,
    val password: String
)