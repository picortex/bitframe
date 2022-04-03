@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.client.signin

import bitframe.core.App
import bitframe.client.ServiceConfig
import bitframe.client.logger
import bitframe.core.RequestBody
import bitframe.core.Session
import bitframe.core.Space
import bitframe.core.signin.SignInParams
import bitframe.core.signin.SignInRawParams
import bitframe.core.signin.SignInResult
import events.Event
import later.Later
import later.await
import later.later
import kotlin.js.JsExport
import kotlin.jvm.JvmStatic
import bitframe.core.signin.SignInServiceCore

abstract class SignInService(
    private val config: ServiceConfig
) : SignInServiceCore {
    val session get() = config.session
    val currentSession get() = session.value
    protected val scope get() = config.scope
    private val logger by config.logger(withSessionInfo = true)

    private val cache get() = config.cache
    private val bus get() = config.bus

    companion object {
        @JvmStatic
        val SESSION_CACHE_KEY = "bitframe.session"

        @JvmStatic
        val CREDENTIALS_CACHE_KEY = "bitframe.credentials"

        @JvmStatic
        val SIGN_IN_EVENT_TOPIC = "bitframe.authentication.sign.in"

        @JvmStatic
        val SWITCH_SPACE_EVENT_TOPIC = "bitframe.authentication.space.switch"

        @JvmStatic
        private fun SignInEvent(session: Session.SignedIn) = Event(session, SIGN_IN_EVENT_TOPIC)

        @JvmStatic
        private fun SwitchSpaceEvent(session: Session.SignedIn) = Event(session, SWITCH_SPACE_EVENT_TOPIC)
    }

    fun loadCachedCredentials() = cache.load<SignInParams>(CREDENTIALS_CACHE_KEY)

    fun signIn(params: SignInRawParams): Later<SignInResult> = scope.later {
        val validCredentials = validate(params).getOrThrow()
        logger.info("Signing `${validCredentials.identifier}` in")
        val rb = RequestBody.UnAuthorized(
            appId = config.appId,
            data = validCredentials
        )
        val conundrum = signIn(rb).await()
        cache.save(CREDENTIALS_CACHE_KEY, validCredentials).await()
        if (conundrum.spaces.size == 1) {
            val (user, spaces) = conundrum
            val s = Session.SignedIn(App(config.appId), spaces.first(), user, spaces)
            finalizeSignIn(s)
        } else {
            session.value = Session.Conundrum(App(config.appId), conundrum.spaces, conundrum.user)
        }
        conundrum
    }

    /**
     * Resolve a [SignInResult] by specifying which [Space] a user currently wants to log in
     *
     * This method should only be called when [signIn] returned a conundrum with at least two [SignInResult.spaces]
     */
    fun resolveConundrum(space: Space): Later<Session.SignedIn> = scope.later {
        logger.info("Resolving conundrum with Space(name=${space.name})")
        val error = IllegalStateException(
            """
                You are attempting to resolve a non exiting conundrum,
                
                Make sure you have tried to signIn and the result obtained was a LoginConundrum with more that one space
                """.trimIndent()
        )
        logger.obj(session.value)
        when (val s = session.value) {
            Session.Unknown -> throw error
            is Session.SignedIn -> Session.SignedIn(App(config.appId), space, s.user, s.spaces)
            is Session.Conundrum -> Session.SignedIn(App(config.appId), space, s.user, s.spaces)
            is Session.SignedOut -> throw error
        }.also { finalizeSignIn(it) }
    }

    private suspend fun finalizeSignIn(s: Session.SignedIn) {
        session.value = s
        cache.save(SESSION_CACHE_KEY, s).await()
        bus.dispatch(SignInEvent(s))
        logger.info("Signed In")
    }

    /**
     * Switch from the current space to the new [space]
     */
    fun switchToSpace(space: Space) = scope.later {
        val error = IllegalStateException(
            """You are attempting to switch spaces while you haven't logged in yet. Make sure you are logged in first,"""
        )
        when (val s = session.value) {
            Session.Unknown -> throw error
            is Session.SignedIn -> Session.SignedIn(App(config.appId), space, s.user, s.spaces)
            is Session.Conundrum -> throw error
            is Session.SignedOut -> throw error
        }.also { finalizeSignIn(it) }
    }

    fun signInWithLastSession(): Later<Session.SignedIn?> = scope.later {
        val cred = cache.load<SignInRawParams>(CREDENTIALS_CACHE_KEY).await()
        val res = signIn(cred).await()
        if (res.spaces.size != 1) {
            val session = cache.load<Session.SignedIn>(SESSION_CACHE_KEY).await()
            resolveConundrum(session.space).await()
        }
        currentSession as? Session.SignedIn
    }
}