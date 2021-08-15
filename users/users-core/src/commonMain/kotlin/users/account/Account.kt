package users.account

import users.ISystemPermission
import users.SystemPermissionGroup
import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val uid: String,
    val name: String,
    val photoUrl: String? = null,
    val scope: String,
    val type: String,
    val deleted: Boolean = false
) {
    interface Type {
        object Proprietor : Type
        object Moderator : Type
        interface Visitor : Type
    }
}