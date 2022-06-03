@file:Suppress("NON_EXPORTABLE_TYPE")

package authenticator

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
}