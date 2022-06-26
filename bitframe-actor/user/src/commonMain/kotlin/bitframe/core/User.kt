@file:UseSerializers(LongAsStringSerializer::class)
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core

import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.LongAsStringSerializer
import kotlin.js.JsExport

@Deprecated("In favour of the one in bitframe.actor")
@JsExport
@Serializable
data class User(
    val name: String,
    val tag: String = name,
    val photoUrl: String? = null,
    val status: Status = Status.SignedOut,
    val registeredOn: Long = Clock.System.now().toEpochMilliseconds(),
    val lastSeen: Long = Clock.System.now().toEpochMilliseconds(),
    override val uid: String = UNSET,
    override val deleted: Boolean = false
) : Savable {
    @Serializable
    sealed class Status {
        @Serializable
        object Blocked : Status()

        @Serializable
        object SignedIn : Status()

        @Serializable
        object SignedOut : Status()
    }

    override fun copySavable(uid: String, deleted: Boolean) = copy(uid = uid, deleted = deleted)

    fun ref() = UserRef(
        uid = uid,
        name = name,
        tag = tag,
        photoUrl = photoUrl
    )
}