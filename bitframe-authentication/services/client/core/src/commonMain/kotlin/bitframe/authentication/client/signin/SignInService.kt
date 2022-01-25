@file:JsExport

package bitframe.authentication.client.signin

import bitframe.authentication.apps.App
import bitframe.authentication.client.SigningServiceConfig
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.Session
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.spaces.Space
import events.Event
import later.Later
import later.await
import later.later
import live.Live
import kotlin.js.JsExport
import kotlin.jvm.JvmStatic
import bitframe.authentication.signin.SignInService as SignInServiceCore

abstract class SignInService(
    open val config: SigningServiceConfig
) : SignInServiceCore() {
    val session: Live<Session> get() = config.signInSession
    val currentSession get() = session.value
    protected val scope get() = config.scope
    private val logger
        get() = config.logger.with(
            "user" to currentSession.user,
            "source" to this::class.simpleName
        )

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
        private fun SignInEvent(session: Session.SignedIn) = Event(session, SIGN_IN_EVENT_TOPIC)
    }

    override fun signIn(cred: SignInCredentials): Later<LoginConundrum> = scope.later {
        val credentials = validate(cred).getOrThrow()

        val conundrum = executeSignIn(cred).await()
        if (conundrum.spaces.size == 1) {
            val (user, spaces) = conundrum
            val s = Session.SignedIn(App(config.appId), spaces.first(), user)
            cache.save(CREDENTIALS_CACHE_KEY, credentials).await()
            finalizeSignIn(s)
        } else {
            session.value = Session.Conundrum(App(config.appId), conundrum.spaces, conundrum.user)
        }
        conundrum
    }

    /**
     * Resolve a [LoginConundrum] by specifying which [Space] a user currently wants to log in
     *
     * This method should only be called when [signIn] returned a conundrum with at least two [LoginConundrum.spaces]
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
            is Session.SignedIn -> Session.SignedIn(App(config.appId), space, s.user)
            is Session.Conundrum -> Session.SignedIn(App(config.appId), space, s.user)
            is Session.SignedOut -> throw error
        }.also { finalizeSignIn(it) }
    }

    private fun finalizeSignIn(s: Session.SignedIn) = scope.later {
        session.value = s
        cache.save(SESSION_CACHE_KEY, s).await()
        bus.dispatch(SignInEvent(s))
    }

    fun signInWithLastSession(): Later<Session.SignedIn?> = scope.later {
        val cred = cache.load<SignInCredentials>(CREDENTIALS_CACHE_KEY).await()
        val res = signIn(cred).await()
        if (res.spaces.size != 1) {
            val session = cache.load<Session.SignedIn>(SESSION_CACHE_KEY).await()
            resolveConundrum(session.space).await()
        }
        currentSession as? Session.SignedIn
    }
}