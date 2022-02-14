package bitframe.client

import bitframe.core.Session
import bitframe.core.Space
import bitframe.core.User
import kotlinx.collections.interoperable.List
import live.Live

class SessionAwareImpl(api: BitframeApi) : SessionAware {
    override val userLiveSession: Live<Session> = api.config.session
    override val userSession get() = userLiveSession.value as? Session.SignedIn
    override val userSpaces: List<Space>? get() = userSession?.spaces
    override val currentUser: User? get() = userSession?.user
    override val currentSpace: Space? get() = userSession?.space
    override val signOut: () -> Unit = { api.signOut.signOut() }
    override val switchSpace: (space: Space) -> Unit = { api.signIn.switchToSpace(it) }
}