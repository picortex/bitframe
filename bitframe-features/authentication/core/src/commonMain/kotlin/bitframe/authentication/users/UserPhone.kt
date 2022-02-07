@file:JsExport

package bitframe.authentication.users

import bitframe.modal.HasId
import identifier.Phone
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class UserPhone(
    override val value: String,
    override val userId: String,
    override val userRef: UserRef,
    override val verified: Boolean = false,
    val whatsapp: Boolean = false,
    override val uid: String = HasId.UNSET,
    override val deleted: Boolean = false,
) : UserContact {

    init {
        // validate the phone
        Phone(value)
    }

    fun asPrimitivePhone() = Phone(value)

    override fun copy(id: String, deleted: Boolean) = copy(uid = id, deleted = deleted)
}