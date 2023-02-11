@file:JsExport

package bitframe

import bitframe.actor.Savable
import bitframe.actor.UNSET
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class UserSpaceInfo(
    val userId: String,
    val spaceId: String,
    val type: UserType,
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copy(uid: String, deleted: Boolean): Savable = copy(uid = uid, deleted = deleted)
}