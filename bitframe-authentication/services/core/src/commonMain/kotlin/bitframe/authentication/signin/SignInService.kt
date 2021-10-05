@file:JsExport

package bitframe.authentication.signin

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
}