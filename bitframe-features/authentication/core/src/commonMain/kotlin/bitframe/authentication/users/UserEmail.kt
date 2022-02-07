package bitframe.authentication.users

import bitframe.modal.HasId
import identifier.Email
import kotlinx.serialization.Serializable

@Serializable
data class UserEmail(
    override val value: String,
    override val userId: String,
    override val userRef: UserRef,
    override val verified: Boolean = false,
    override val uid: String = HasId.UNSET,
    override val deleted: Boolean = false,
) : UserContact {
    init {
        // validate the email
        Email(value)
    }

    fun asPrimitiveEmail() = Email(value)

    override fun copy(id: String, deleted: Boolean) = copy(uid = id, deleted = deleted)
}