package bitframe.api

import bitframe.authentication.signin.Session
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import live.Live
import live.value

interface SessionAware {
    val userLiveSession: Live<Session>
    val userSession get() = userLiveSession.value as? Session.SignedIn
    val currentUser: User?
    val currentSpace: Space?
    val signOut: () -> Unit
    val switchSpace: (space: Space) -> Unit
}