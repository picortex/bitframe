@file:JsExport

package bitframe.authentication.signin

import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import later.Later
import live.Live
import kotlin.js.JsExport
import kotlin.jvm.JvmField
import kotlin.jvm.JvmStatic

abstract class SignInService {
    companion object {
        val session: Live<Session> = Live(Session.Unknown)

        val current get() = session.value
    }

    fun validate(credentials: SignInCredentials) {
        require(credentials.alias.isNotEmpty()) { "loginId (i.e. email/phone/username), must not be empty" }
        require(credentials.password.isNotEmpty()) { "Password must not be empty" }
    }

    abstract fun signIn(credentials: SignInCredentials): Later<LoginConundrum>

    /**
     * Resolve a [LoginConundrum] by specifying which [Space] a user currently wants to log in
     *
     * This method should only be called when [signIn] returned a conundrum with at least two [LoginConundrum.spaces]
     */
    abstract fun resolve(space: Space): Later<Session.SignedIn>
}