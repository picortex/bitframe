@file:JsExport

package bitframe.actors.users

import bitframe.actors.modal.HasId
import identifier.Email
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class UserEmail(
    override val value: String,
    override val userId: String,
    override val userRef: UserRef,
    override val verified: Boolean = false,
    override val uid: String = HasId.UNSET,
    override val deleted: Boolean = false,
) : UserContact() {
    init {
        // validate the email
        Email(value)
    }

    fun asPrimitiveEmail() = Email(value)

    override fun copy(id: String, deleted: Boolean) = copy(uid = id, deleted = deleted)
}