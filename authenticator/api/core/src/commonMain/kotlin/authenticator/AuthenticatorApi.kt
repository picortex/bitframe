@file:Suppress("NON_EXPORTABLE_TYPE")

package authenticator

import bitframe.core.signin.SignInParams
import bitframe.core.signin.SignInResult
import later.Later
import kotlin.js.JsExport

@JsExport
interface AuthenticatorApi {
    val session: SessionAware

    val users: UsersService

    fun signIn(params: SignInParams): Later<SignInResult>
    fun signOut()
}