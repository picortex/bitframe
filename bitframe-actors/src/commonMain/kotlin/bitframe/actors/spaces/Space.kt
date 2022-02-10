@file:JsExport

package bitframe.actors.spaces

import bitframe.actors.modal.HasId
import bitframe.actors.modal.Savable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Space(
    val name: String,
    val type: SpaceType,
    val photoUrl: String? = null,
    override val uid: String = HasId.UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}