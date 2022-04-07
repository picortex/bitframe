@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.signout

import bitframe.client.ServiceConfig
import bitframe.client.events.SignedOutEvent
import bitframe.client.logger
import bitframe.client.signin.SignInService
import bitframe.core.Session
import kotlinx.coroutines.launch
import later.await
import kotlin.js.JsExport

class SignOutService(private val config: ServiceConfig) {
    private val logger by config.logger(withSessionInfo = true)

    fun signOut() {
        logger.info("Signing out . . .")
        val signInSession = config.session
        val session = signInSession.value as? Session.SignedIn ?: return

        signInSession.value = Session.SignedOut(
            app = session.app,
            space = session.space,
            user = session.user,
            spaces = session.spaces
        )

        config.scope.launch {
            val cache = config.cache
            cache.remove(SignInService.SESSION_CACHE_KEY).await()
            cache.remove(SignInService.CREDENTIALS_CACHE_KEY).await()

            val bus = config.bus
            bus.dispatch(SignedOutEvent(session))
        }

        logger.info("Signed out")
    }
}