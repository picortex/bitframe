@file:JsExport

package bitframe.authentication.spaces

import bitframe.modal.Savable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Space(
    val name: String,
    val photoUrl: String? = null,
    val scope: String,
    val type: String,
    override val uid: String = "",
    override val deleted: Boolean = false
) : Savable {
    override fun copy(id: String, deleted: Boolean) = copy(uid = id, deleted = deleted)
}