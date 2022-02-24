@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client

import bitframe.core.Space
import bitframe.core.User
import bitframe.core.Session
import kotlinx.collections.interoperable.List
import live.Live
import kotlin.js.JsExport

@JsExport
interface SessionAware {
    val live: Live<Session>
    val signedIn: Session.SignedIn?
    val userSpaces: List<Space>?
    val currentUser: User?
    val currentSpace: Space?
    val signOut: () -> Unit
    val switchSpace: (space: Space) -> Unit
}