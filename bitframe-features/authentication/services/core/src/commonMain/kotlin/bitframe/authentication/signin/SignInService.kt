@file:JsExport

package bitframe.authentication.signin

import later.Later
import validation.Validation
import validation.validate
import kotlin.js.JsExport

abstract class SignInService {

    open fun validate(credentials: SignInCredentials): Validation<SignInCredentials> = validate {
        require(credentials.identifier.isNotEmpty()) { "loginId (i.e. email/phone/username), must not be empty" }
        require(credentials.password.isNotEmpty()) { "Password must not be empty" }
        credentials
    }

    /**
     * Do not call this method directly. Call [signIn] instead
     */
    protected abstract fun executeSignIn(credentials: SignInCredentials): Later<LoginConundrum>

    abstract fun signIn(cred: SignInCredentials): Later<LoginConundrum>
}