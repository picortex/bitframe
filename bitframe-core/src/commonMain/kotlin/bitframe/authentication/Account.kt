package bitframe.authentication

import bitframe.annotations.Generated
import kotlinx.serialization.Serializable

@Serializable
data class Account(
    @Generated val uid: String,
    val name: String = "John Doe"
)