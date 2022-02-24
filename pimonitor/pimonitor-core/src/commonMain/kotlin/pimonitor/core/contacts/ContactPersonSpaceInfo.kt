@file:JsExport

package pimonitor.core.contacts

import bitframe.core.Savable
import bitframe.core.UNSET
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class ContactPersonSpaceInfo(
    val userId: String,
    val spaceId: String,
    val owningSpaceId: String,
    val position: String,
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}