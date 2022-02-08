@file:JsExport
@file:UseSerializers(LongAsStringSerializer::class)

package bitframe.actors.users

import bitframe.actors.modal.HasId
import bitframe.actors.modal.Savable
import bitframe.actors.spaces.Space
import kotlinx.collections.interoperable.List
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.LongAsStringSerializer
import kotlin.js.JsExport

@Serializable
data class User(
    val name: String,
    val tag: String = name,
    val contacts: List<UserContact>,
    val spaces: List<Space>,
    val photoUrl: String? = null,
    val status: Status = Status.SignedOut,
    val registeredOn: Long = Clock.System.now().toEpochMilliseconds(),
    val lastSeen: Long = Clock.System.now().toEpochMilliseconds(),
    override val uid: String = HasId.UNSET,
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

    override fun copy(id: String, deleted: Boolean) = copy(uid = id, deleted = deleted)

    fun ref() = UserRef(
        uid = uid,
        name = name,
        tag = tag,
        photoUrl = photoUrl
    )
}