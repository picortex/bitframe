package bitframe.authentication

import bitframe.annotations.Generated
import bitframe.annotations.Module
import kotlinx.serialization.Serializable

@Module(name = "profile")
@Serializable
data class Account(
    @Generated val uid: String,
    val name: String = "John Doe"
)