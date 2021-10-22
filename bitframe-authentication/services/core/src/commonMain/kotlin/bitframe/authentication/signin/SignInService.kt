package bitframe.authentication.signin

import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import bitframe.events.Event
import bitframe.events.EventBus
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import later.later
import live.Live
import kotlin.js.JsExport

abstract class SignInService(
    open val bus: EventBus,
    protected open val config: ServiceConfig
) {
    val session: Live<Session> = Live(Session.Unknown)
    val currentSession get() = session.value
    protected val scope get() = config.scope

    companion object {
        const val SIGN_IN_EVENT_ID = "bitframe.authentication.sign.in"
        const val SIGN_OUT_EVENT_ID = "bitframe.authentication.sign.out"

        fun signInEvent(session: Session.SignedIn) = Event(SIGN_IN_EVENT_ID, session)
    }

    fun validate(credentials: SignInCredentials) {
        require(credentials.alias.isNotEmpty()) { "loginId (i.e. email/phone/username), must not be empty" }
        require(credentials.password.isNotEmpty()) { "Password must not be empty" }
    }

    /**
     * Do not call this method directly. Call [signIn] instead
     */
    protected abstract fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum>

    fun signIn(credentials: SignInCredentials): Later<LoginConundrum> = scope.later {
        validate(credentials)
        val conundrum = executeSignIn(credentials).await()
        if (conundrum.spaces.size == 1) {
            val (user, spaces) = conundrum
            val s = Session.SignedIn(App(config.appId), spaces.first(), user)
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
        }
    }
}