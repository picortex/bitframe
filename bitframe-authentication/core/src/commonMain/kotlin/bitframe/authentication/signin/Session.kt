@file:JsExport

package bitframe.authentication.signin

import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import kotlinx.serialization.Serializable
import kotlin.js.JsExport

sealed class Session {
    object Unknown : Session()

    @Serializable
    data class SignedIn(
        val app: App,
        val space: Space,
        val user: User
    ) : Session()

    data class Conundrum(
        val app: App,
        val spaces: List<Space>,
        val user: User
    ) : Session()
    
    data class SignedOut(
        val app: App,
        val space: Space?,
        val user: User?
    ) : Session()
}