@file:JsExport

package bitframe.authentication.client.signout

import bitframe.authentication.client.SigningServiceConfig
import bitframe.authentication.signin.Session
import kotlin.js.JsExport

class SignOutService(private val config: SigningServiceConfig) {
    private val logger
        get() = config.logger.with(
            "User" to (config.signInSession.value as? Session.SignedIn)?.user?.name,
            "Source" to SignOutService::class.simpleName
        )

    fun signOut() {
        // TODO: Clear add clear cache methods please
        logger.info("Signing out . . .")
        config.signInSession.value = when (val session = config.signInSession.value) {
            Session.Unknown -> Session.Unknown
            is Session.SignedIn -> Session.SignedOut(
                app = session.app,
                space = session.space,
                user = session.user
            )
            is Session.Conundrum -> Session.SignedOut(
                app = session.app,
                space = null,
                user = session.user
            )
            is Session.SignedOut -> session
        }
        logger.info("Signed out")
    }
}