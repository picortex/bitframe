@file:JsExport

package bitframe.core

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Deprecated("in favour of bitframe.Space")
@Serializable
data class Space(
    val name: String,
    val type: SpaceType,
    val photoUrl: String? = null,
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}