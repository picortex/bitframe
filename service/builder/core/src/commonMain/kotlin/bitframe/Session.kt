@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe

import bitframe.actor.App
import bitframe.actor.Space
import bitframe.actor.User
import kotlinx.serialization.Serializable
import kotlinx.collections.interoperable.List
import kotlin.js.JsExport

sealed class Session {
    abstract val user: User?
    abstract val space: Space?

    object Unknown : Session() {
        override val space: Space? = null
        override val user: User? = null
    }

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
    ) : Session() {
        override val space: Space? = null
    }

    data class SignedOut(
        val app: App,
        override val space: Space?,
        val spaces: List<Space>,
        override val user: User?
    ) : Session()
}