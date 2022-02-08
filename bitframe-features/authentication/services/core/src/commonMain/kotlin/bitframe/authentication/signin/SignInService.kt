@file:JsExport

package bitframe.authentication.signin

import bitframe.actors.modal.Identifier
import bitframe.service.requests.RequestBody
import later.Later
import validation.Validation
import validation.validate
import kotlin.js.JsExport

abstract class SignInService {

    open fun validate(credentials: SignInCredentials): Validation<SignInCredentials> = validate {
        require(credentials.identifier.isNotEmpty()) { "login identifier (i.e. email/phone), must not be empty" }
        require(credentials.password.isNotEmpty()) { "Password must not be empty" }
        Identifier.from(credentials.identifier)
        credentials
    }

    protected abstract fun signIn(rb: RequestBody.UnAuthorized<SignInCredentials>): Later<SignInResult>
}