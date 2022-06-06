package bitframe.client

import bitframe.core.Session
import bitframe.core.Space
import bitframe.core.User
import kotlinx.collections.interoperable.List
import live.Live

class SessionAwareImpl(private val api: BitframeApi) : SessionAware {
    override val live: Live<Session> get() = api.signIn.session
    override val signedIn get() = live.value as? Session.SignedIn
    override val userSpaces: List<Space>? get() = signedIn?.spaces
    override val currentUser: User? get() = signedIn?.user
    override val currentSpace: Space? get() = signedIn?.space
    override val signOut: () -> Unit = { api.signOut.signOut() }
    override val switchSpace: (space: Space) -> Unit = { api.signIn.switchToSpace(it) }
}