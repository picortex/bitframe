@file:JsExport

package bitframe.authentication.client.signin

import bitframe.authentication.apps.App
import bitframe.authentication.signin.LoginConundrum
import bitframe.authentication.signin.Session
import bitframe.authentication.signin.SignInCredentials
import bitframe.authentication.spaces.Space
import bitframe.events.Event
import later.Later
import later.await
import later.later
import live.Live
import kotlin.js.JsExport
import bitframe.authentication.signin.SignInService as SignInServiceCore

abstract class SignInService(
    open val config: SignInServiceConfig
) : SignInServiceCore() {
    val session: Live<Session> = Live(Session.Unknown)
    val currentSession get() = session.value
    protected val scope get() = config.scope

    private val cache get() = config.cache
    private val bus get() = config.bus

    companion object {
        const val SESSION_CACHE_KEY = "bitframe.session"
        const val CREDENTIALS_CACHE_KEY = "bitframe.credentials"
        const val SIGN_IN_EVENT_ID = "bitframe.authentication.sign.in"
        const val SIGN_OUT_EVENT_ID = "bitframe.authentication.sign.out"

        fun signInEvent(session: Session.SignedIn) = Event(SIGN_IN_EVENT_ID, session)
    }

    override fun signIn(cred: SignInCredentials): Later<LoginConundrum> = scope.later {
        val credentials = validate(cred).getOrThrow()

        val conundrum = executeSignIn(cred).await()
        if (conundrum.spaces.size == 1) {
            val (user, spaces) = conundrum
            val s = Session.SignedIn(App(config.appId), spaces.first(), user)
            cache.save(CREDENTIALS_CACHE_KEY, credentials).await()
            cache.save(SESSION_CACHE_KEY, s).await()
            session.value = s
            bus.dispatch(signInEvent(s))
        }
        conundrum
    }

    /**
     * Resolve a [LoginConundrum] by specifying which [Space] a user currently wants to log in
     *
     * This method should only be called when [signIn] returned a conundrum with at least two [LoginConundrum.spaces]
     */
    fun resolve(space: Space): Later<Session.SignedIn> = scope.later {
        val error = IllegalStateException(
            """
                You are attempting to resolve a non exiting conundrum,
                
                Make sure you have tried to signIn and the result obtained was a LoginConundrum with more that one space
                """.trimIndent()
        )
        when (val s = session.value) {
            Session.Unknown -> throw error
            is Session.SignedIn -> Session.SignedIn(App(config.appId), space, s.user)
            is Session.Conundrum -> Session.SignedIn(App(config.appId), space, s.user)
            is Session.SignedOut -> throw error
        }.also {
            session.value = it
            bus.dispatch(signInEvent(it))
            cache.save(SESSION_CACHE_KEY, it).await()
        }
    }
}