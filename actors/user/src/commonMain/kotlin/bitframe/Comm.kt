@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe

import bitframe.actor.Identifier
import bitframe.actor.Savable
import bitframe.actor.UNSET
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.jvm.JvmOverloads
import kotlin.jvm.JvmStatic


/**
 * A class representing a way of communicating
 *  currently supports email addresses and phone numbers
 */
@Serializable
sealed class Comm : Savable {
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
            uid: String = UNSET,
            deleted: Boolean = false,
        ): Comm = when (val id = Identifier.from(value)) {
            is Identifier.Phone -> UserPhone(
                value = id.value, userId, verified, whatsapp = false, uid, deleted
            )

            is Identifier.Email -> UserEmail(
                value = id.value, userId, verified, uid, deleted
            )
        }
    }
}