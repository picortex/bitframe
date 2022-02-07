package bitframe.api

import bitframe.actors.spaces.Space
import bitframe.actors.users.User
import bitframe.service.client.Session
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