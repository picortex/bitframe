package bitframe.authentication.spaces

import bitframe.actors.spaces.SpacePermission
import kotlinx.serialization.Serializable

@Serializable
class CreateSpaceScopeParams(
    val name: String,
    val permissions: List<SpacePermission>
) {
    constructor(name: String, vararg permissions: SpacePermission) : this(name, permissions.toList())
}