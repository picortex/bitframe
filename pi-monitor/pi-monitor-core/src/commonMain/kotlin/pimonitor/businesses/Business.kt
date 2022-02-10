package pimonitor.businesses

import bitframe.actors.modal.HasId
import bitframe.actors.modal.Savable
import kotlinx.serialization.Serializable

@Serializable
data class Business(
    val spaceId: String,
    val type: BusinessType,
    val email: String = "",
    val address: String = "",
    val logo: String? = "",
    override val uid: String = HasId.UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}