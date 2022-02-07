@file:JsExport

package bitframe.actors.users

import kotlinx.serialization.Serializable
import kotlin.js.JsExport

@Serializable
data class UserRef(
    val uid: String,
    val name: String,
    val tag: String,
    val photoUrl: String?
)