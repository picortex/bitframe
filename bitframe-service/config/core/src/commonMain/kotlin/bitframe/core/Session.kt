@file:JsExport

package bitframe.core

import kotlinx.serialization.Serializable
import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

sealed class Session {
    object Unknown : Session()

    @Serializable
    data class SignedIn(
        val app: App,
        override val space: Space,
        override val user: User,
        val spaces: List<Space>,
    ) : Session()

    data class Conundrum(
        val app: App,
        val spaces: List<Space>,
        override val user: User
    ) : Session()

    data class SignedOut(
        val app: App,
        override val space: Space?,
        val spaces: List<Space>,
        override val user: User?
    ) : Session()

    open val user: User?
        get() = when (this) {
            Unknown -> null
            is SignedIn -> user
            is Conundrum -> user
            is SignedOut -> null
        }

    open val space: Space?
        get() = when (this) {
            Unknown -> null
            is SignedIn -> space
            is Conundrum -> null
            is SignedOut -> null
        }
}