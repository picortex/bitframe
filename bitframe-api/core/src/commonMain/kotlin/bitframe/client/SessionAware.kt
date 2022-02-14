@file:JsExport

package bitframe.client

import bitframe.core.Space
import bitframe.core.User
import bitframe.core.Session
import kotlinx.collections.interoperable.List
import live.Live
import kotlin.js.JsExport

interface SessionAware {
    val userLiveSession: Live<Session>
    val userSession: Session.SignedIn?
    val userSpaces: List<Space>?
    val currentUser: User?
    val currentSpace: Space?
    val signOut: () -> Unit
    val switchSpace: (space: Space) -> Unit
}