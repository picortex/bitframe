package bitframe.actors.spaces

import kotlinx.serialization.Serializable

@Serializable
data class SpacePermission(
    val name: String,
    val details: String,
    val needs: List<String> = listOf()
) {
    companion object {
        val DEV: SpacePermission = SpacePermission(
            name = "system.developer",
            details = "Grant's permissions to every action on the system (Should only be used in development)"
        )
    }
}