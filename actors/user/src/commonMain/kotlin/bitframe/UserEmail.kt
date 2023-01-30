@file:JsExport

package bitframe

import bitframe.actor.UNSET
import identifier.Email
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class UserEmail(
    override val value: String,
    override val userId: String,
    override val verified: Boolean = false,
    override val uid: String = UNSET,
    override val deleted: Boolean = false,
) : Comm() {
    init {
        // validate the email
        Email(value)
    }

    fun asPrimitiveEmail() = Email(value)

    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)
}