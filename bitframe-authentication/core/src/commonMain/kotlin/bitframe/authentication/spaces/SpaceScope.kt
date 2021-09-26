package bitframe.authentication.spaces

import kotlinx.serialization.Serializable

@Serializable
data class SpaceScope(
    val uid: String,
    val name: String,
    val permissions: List<SpacePermission>
)