@file:JsExport

package bitframe.authentication.signin

import later.Later
import kotlin.js.JsExport

abstract class SignInService {
    fun validate(credentials: SignInCredentials) {
        require(credentials.alias.isNotEmpty()) { "loginId (i.e. email/phone/username), must not be empty" }
        require(credentials.password.isNotEmpty()) { "Password must not be empty" }
    }

    abstract fun signIn(credentials: SignInCredentials): Later<LoginConundrum>
}