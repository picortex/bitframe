package bitframe.authentication.spaces

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class CreateSpaceScopeParams(
    val name: String,
    val permissions: List<SpacePermission>
) {
    constructor(name: String, vararg permissions: SpacePermission) : this(name, permissions.toList())
}