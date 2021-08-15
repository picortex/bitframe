package users.account

import kotlinx.serialization.Serializable

@Serializable
class CreateAccountParams(
    val name: String
)