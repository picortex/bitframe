@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.actors.users

import bitframe.actors.modal.HasId
import bitframe.actors.modal.Identifier
import bitframe.actors.modal.Savable
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic

@Serializable
sealed class UserContact : Savable {
    abstract val verified: Boolean
    abstract val userId: String
    abstract val value: String

    companion object {
        @JvmStatic
        @JvmOverloads
        fun of(
            value: String,
            userId: String,
            verified: Boolean = false,
            uid: String = HasId.UNSET,
            deleted: Boolean = false,
        ): UserContact = when (val id = Identifier.from(value)) {
            is Identifier.Phone -> UserPhone(
                value = id.value, userId, verified, whatsapp = false, uid, deleted
            )
            is Identifier.Email -> UserEmail(
                value = id.value, userId, verified, uid, deleted
            )
        }
    }
}