@file:JsExport
@file:UseSerializers(LongAsStringSerializer::class)

package bitframe.authentication.users

import bitframe.authentication.ISystemPermission
import bitframe.authentication.spaces.Space
import bitframe.modal.HasId
import bitframe.modal.Savable
import kotlinx.datetime.Clock
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.builtins.LongAsStringSerializer
import kotlinx.collections.interoperable.List
import kotlinx.collections.interoperable.listOf
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
data class User(
    override val uid: String = "",
    val name: String,
    val tag: String = name,
    val contacts: Contacts,
    val photoUrl: String? = null,
    val spaces: List<Space>,
    val status: Status = Status.SignedOut,
    val registeredOn: Long = Clock.System.now().toEpochMilliseconds(),
    val lastSeen: Long = Clock.System.now().toEpochMilliseconds(),
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

    @Serializable
    class Permissions(
        override val title: String,
        override val details: String,
        override val needs: List<String> = listOf(),
    ) : ISystemPermission {
        companion object {
            val Read = Permissions(
                title = "acceptance.authentication.users.read",
                details = "Grants access to view/edit users in the system"
            )
            val Create = Permissions(
                title = "acceptance.authentication.users.create",
                details = "Grants access to create different users for the system",
                needs = listOf(Read.title)
            )
            val Update = Permissions(
                title = "acceptance.authentication.users.update",
                details = "Grants access to update user information",
                needs = listOf(Read.title)
            )
            val Delete = Permissions(
                title = "acceptance.authentication.users.delete",
                details = "Grants access to delete users from the system",
                needs = listOf(Read.title)
            )
            val Wipe = Permissions(
                title = "acceptance.authentication.users.wipe",
                details = "Grants access to permanently wipe users from the system",
                needs = listOf(Read.title)
            )
        }
    }

    fun ref() = UserRef(
        uid = uid,
        name = name,
        tag = tag,
        contacts = contacts,
        photoUrl = photoUrl
    )
}