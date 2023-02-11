@file:JsExport

package bitframe.actor

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class Space(
    val name: String,
    val type: SpaceType,
    val photoUrl: String? = null,
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copy(uid: String, deleted: Boolean) = copy(uid = uid, name = name, deleted = deleted)
}