package bitframe.authentication.signin

import bitframe.authentication.apps.App
import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import bitframe.service.config.ServiceConfig
import later.Later
import later.await
import later.later
import live.Live
import kotlin.js.JsExport

abstract class SignInService<D> {
    protected abstract val config: ServiceConfig
    val session: Live<Session<D>> = Live(Session.Unknown)
    val currentSession get() = session.value

    protected val scope get() = config.scope

    fun validate(credentials: SignInCredentials) {
        require(credentials.alias.isNotEmpty()) { "loginId (i.e. email/phone/username), must not be empty" }
        require(credentials.password.isNotEmpty()) { "Password must not be empty" }
    }

    /**
     * Do not call this method directly. Call [signIn] instead
     */
    abstract fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum>

    fun signIn(credentials: SignInCredentials): Later<LoginConundrum> {
        validate(credentials)
        return executeSignIn(credentials)
    }

    protected abstract fun makeSession(app: App, space: Space, user: User): Later<Session.SignedIn<D>>

    /**
     * Resolve a [LoginConundrum] by specifying which [Space] a user currently wants to log in
     *
     * This method should only be called when [signIn] returned a conundrum with at least two [LoginConundrum.spaces]
     */
    fun resolve(space: Space): Later<Session.SignedIn<*>> = scope.later {
        val error = IllegalStateException(
            """
                You are attempting to resolve a non exiting conundrum,
                
                Make sure you have tried to signIn and the result obtained was a LoginConundrum with more that one space
                """.trimIndent()
        )
        when (val s = session.value) {
            Session.Unknown -> throw error
            is Session.SignedIn -> makeSession(App(config.appId), space, s.user).await()
            is Session.Conundrum -> makeSession(App(config.appId), space, s.user).await()
            is Session.SignedOut -> throw error
        }.also { session.value = it }
    }
}