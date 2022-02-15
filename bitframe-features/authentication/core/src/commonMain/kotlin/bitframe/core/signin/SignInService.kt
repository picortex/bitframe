@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core.signin

import validation.Validation
import validation.validate
import kotlin.js.JsExport

@JsExport
abstract class SignInService : SignInUseCase {
    open fun validate(credentials: RawSignInCredentials): Validation<SignInCredentials> = validate {
        require(credentials.identifier?.isNotEmpty() == true) { "login identifier (i.e. email/phone), must not be empty" }
        require(credentials.password.isNotEmpty()) { "Password must not be empty" }
        credentials.toSignInCredentials()
    }
}