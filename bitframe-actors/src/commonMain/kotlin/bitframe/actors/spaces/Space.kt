@file:JsExport

package bitframe.actors.spaces

import bitframe.actors.modal.Savable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Space(
    val name: String,
    val scope: String,
    val type: String,
    val photoUrl: String? = null,
    override val uid: String = "",
    override val deleted: Boolean = false
) : Savable {
    override fun copy(id: String, deleted: Boolean) = copy(uid = id, deleted = deleted)
}