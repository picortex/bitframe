@file:JsExport

package bitframe.authentication.signin

import validation.Validation
import validation.validate
import kotlin.js.JsExport

abstract class SignInService : SignInUseCase {
    open fun validate(credentials: IRawSignInCredentials): Validation<RawSignInCredentials> = validate {
        val cred = credentials.toSignInCredentials()
        require(cred.identifier.isNotEmpty()) { "login identifier (i.e. email/phone), must not be empty" }
        require(credentials.password.isNotEmpty()) { "Password must not be empty" }
        credentials.toSignInCredentials()
    }
}