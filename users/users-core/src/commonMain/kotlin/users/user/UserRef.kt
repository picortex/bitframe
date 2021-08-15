package users.user

import kotlinx.serialization.Serializable

@Serializable
class UserRef(
    val uid: String,
    val name: String,
    val tag: String,
    val contacts: Contacts,
    val photoUrl: String?
)