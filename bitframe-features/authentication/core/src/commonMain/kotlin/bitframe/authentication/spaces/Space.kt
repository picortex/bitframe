@file:JsExport

package bitframe.authentication.spaces

import bitframe.modal.HasId
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Space(
    val name: String,
    val photoUrl: String? = null,
    val scope: String,
    val type: String,
    override val uid: String = "",
    val deleted: Boolean = false
) : HasId {
    override fun copy(id: String): HasId = copy(uid = id)
}