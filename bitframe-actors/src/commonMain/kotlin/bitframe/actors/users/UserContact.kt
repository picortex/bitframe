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
    abstract val userRef: UserRef
    abstract val value: String

    companion object {
        @JvmStatic
        @JvmOverloads
        fun of(
            value: String,
            userId: String,
            userRef: UserRef,
            verified: Boolean = false,
            uid: String = HasId.UNSET,
            deleted: Boolean = false,
        ): UserContact = when (val id = Identifier.from(value)) {
            is Identifier.Phone -> UserPhone(
                id.value, userId, userRef, verified, whatsapp = false, uid, deleted
            )
            is Identifier.Email -> UserEmail(
                id.value, userId, userRef, verified, uid, deleted
            )
        }
    }
}