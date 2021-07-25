package bitframe.authentication

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val uid: String,
    val name: String
)