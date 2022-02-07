@file:JsExport
@file:Suppress("WRONG_EXPORTED_DECLARATION")

package bitframe.actors.users

import bitframe.actors.modal.Savable
import kotlin.js.JsExport

sealed interface UserContact : Savable {
    val verified: Boolean
    val userId: String
    val userRef: UserRef
    val value: Any
}