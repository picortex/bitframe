@file:JsExport

package bitframe.actors.users

import bitframe.actors.modal.HasId
import bitframe.actors.modal.Savable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class UserSpaceInfo(
    val userId: String,
    val spaceId: String,
    val type: UserType,
    override val uid: String = HasId.UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean): Savable = copy(uid = uid, deleted = deleted)
}