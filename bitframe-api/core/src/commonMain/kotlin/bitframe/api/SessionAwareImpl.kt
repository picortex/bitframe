package bitframe.api

import bitframe.actors.spaces.Space
import bitframe.actors.users.User
import bitframe.service.Session
import live.Live
import live.value

class SessionAwareImpl(service: BitframeService) : SessionAware {
    override val userLiveSession: Live<Session> = service.config.session
    val currentSignInSession get() = userLiveSession.value as? Session.SignedIn
    override val currentUser: User? get() = currentSignInSession?.user
    override val currentSpace: Space? get() = currentSignInSession?.space
    override val signOut: () -> Unit = { service.signOut.signOut() }
    override val switchSpace: (space: Space) -> Unit = { service.signIn.switchToSpace(it) }
}