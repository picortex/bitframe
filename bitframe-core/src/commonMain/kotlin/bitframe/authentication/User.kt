package bitframe.authentication

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String
)