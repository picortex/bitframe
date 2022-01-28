@file:JsExport

package bitframe.authentication.client.signout

import bitframe.authentication.client.SigningServiceConfig
import bitframe.authentication.client.signin.SignInService
import bitframe.authentication.signin.Session
import events.Event
import kotlinx.coroutines.launch
import later.await
import live.value
import kotlin.js.JsExport
import kotlin.jvm.JvmStatic

class SignOutService(private val config: SigningServiceConfig) {
    private val logger
        get() = config.logger.with(
            "User" to (config.signInSession.value as? Session.SignedIn)?.user?.name,
            "Source" to SignOutService::class.simpleName
        )

    companion object {
        @JvmStatic
        val SIGN_OUT_EVENT_TOPIC = "bitframe.authentication.sign.out"

        @JvmStatic
        fun SignOutEvent(session: Session.SignedIn) = Event(session, SIGN_OUT_EVENT_TOPIC)
    }

    fun signOut() {
        logger.info("Signing out . . .")
        val signInSession = config.signInSession
        val session = signInSession.value as? Session.SignedIn ?: return
        
        signInSession.value = Session.SignedOut(
            app = session.app,
            space = session.space,
            user = session.user
        )

        config.scope.launch {
            val cache = config.cache
            cache.remove(SignInService.SESSION_CACHE_KEY).await()
            cache.remove(SignInService.CREDENTIALS_CACHE_KEY).await()

            val bus = config.bus
            bus.dispatch(SignOutEvent(session))
        }

        logger.info("Signed out")
    }
}