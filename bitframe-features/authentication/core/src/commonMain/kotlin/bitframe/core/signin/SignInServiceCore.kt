@file:Suppress("NON_EXPORTABLE_TYPE")

package bitframe.core.signin

import bitframe.core.RequestBody
import later.Later
import validation.Validation
import validation.validate
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface SignInServiceCore {
    @JsName("_ignore_signIn")
    fun signIn(rb: RequestBody.UnAuthorized<SignInRawParams>): Later<SignInResult>
    open fun validate(params: SignInRawParams): Validation<SignInRawParams> = validate {
        require(params.identifier?.isNotEmpty() == true) { "login identifier (i.e. email/phone), must not be empty" }
        require(params.password.isNotEmpty()) { "Password must not be empty" }
        params.toSignInParams()
    }
}