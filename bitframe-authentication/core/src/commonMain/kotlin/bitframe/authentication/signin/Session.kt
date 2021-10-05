@file:JsExport

package bitframe.authentication.signin

import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import kotlin.js.JsExport

sealed class Session {
    object Unknown : Session()

    class SignedIn(
        val app: App,
        val space: Space,
        val user: User,
    ) : Session()

    class Conundrum(
        val app: App,
        val spaces: List<Space>,
        val user: User
    ) : Session()

    class SignedOut(
        val app: App,
        val space: Space?,
        val user: User?,
    ) : Session()
}