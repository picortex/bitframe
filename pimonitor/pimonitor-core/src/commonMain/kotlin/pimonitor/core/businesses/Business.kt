package pimonitor.core.businesses

import bitframe.core.Savable
import bitframe.core.UNSET
import kotlinx.serialization.Serializable

@Serializable
data class Business(
    val spaceId: String,
    val type: BusinessType,
    val email: String = "",
    val address: String = "",
    val logo: String? = "",
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}