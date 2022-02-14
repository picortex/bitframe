package bitframe.core.users

import bitframe.core.Savable
import bitframe.core.UNSET
import kotlinx.serialization.Serializable

@Serializable
data class UserCredentials(
    val userId: String,
    val credential: String,
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}