@file:JsExport

package bitframe.authentication.users

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
class UserRef(
    val uid: String,
    val name: String,
    val tag: String,
    val contacts: Contacts,
    val photoUrl: String?
)