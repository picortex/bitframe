@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.signup

import bitframe.core.RequestBody
import later.Later
import pimonitor.core.signup.params.SignUpBusinessParams
import pimonitor.core.signup.params.SignUpIndividualParams
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
interface SignUpServiceCore {
    @JsName("_ignore_signUpAsIndividual")
    fun signUpAsIndividual(rb: RequestBody.UnAuthorized<SignUpIndividualParams>): Later<SignUpResult>

    @JsName("_ignore_signUpAsBusiness")
    fun signUpAsBusiness(rb: RequestBody.UnAuthorized<SignUpBusinessParams>): Later<SignUpResult>
}