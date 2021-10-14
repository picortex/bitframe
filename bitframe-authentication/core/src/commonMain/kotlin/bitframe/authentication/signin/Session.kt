@file:JsExport

package bitframe.authentication.signin

import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import kotlin.js.JsExport

sealed class Session<out T> {
    object Unknown : Session<Nothing>()

    class SignedIn<out T>(
        val app: App,
        val space: Space,
        val user: User,
        val data: T
    ) : Session<T>()

    class Conundrum(
        val app: App,
        val spaces: List<Space>,
        val user: User
    ) : Session<Nothing>()

    class SignedOut<out T>(
        val app: App,
        val space: Space?,
        val user: User?,
        val data: T?
    ) : Session<T>()
}