@file:JsExport

package bitframe.core

import identifier.Phone
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Deprecated("In favour of the one in bitframe.actor")
@Serializable
data class UserPhone(
    override val value: String,
    override val userId: String,
    override val verified: Boolean = false,
    val whatsapp: Boolean = false,
    override val uid: String = UNSET,
    override val deleted: Boolean = false,
) : UserContact() {

    init {
        // validate the phone
        Phone(value)
    }

    fun asPrimitivePhone() = Phone(value)

    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}