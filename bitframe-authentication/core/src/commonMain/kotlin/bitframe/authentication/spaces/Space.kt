package bitframe.authentication.spaces

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Space(
    val uid: String,
    val name: String,
    val photoUrl: String? = null,
    val scope: String,
    val type: String,
    val deleted: Boolean = false
) {
    interface Type {
        object Proprietor : Type
        object Moderator : Type
        interface Visitor : Type
    }
}