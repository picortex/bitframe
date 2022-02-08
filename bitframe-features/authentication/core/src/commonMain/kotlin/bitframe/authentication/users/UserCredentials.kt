package bitframe.authentication.users

import bitframe.actors.modal.HasId
import bitframe.actors.modal.Savable
import kotlinx.serialization.Serializable

@Serializable
data class UserCredentials(
    val userId: String,
    val credential: String,
    override val uid: String = HasId.UNSET,
    override val deleted: Boolean = false
) : Savable {
    override fun copy(id: String, deleted: Boolean) = copy(uid = id, deleted = deleted)
}