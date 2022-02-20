@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.signup

import bitframe.core.RequestBody
import bitframe.core.ServiceConfig
import events.Event
import identifier.Email
import later.Later
import pimonitor.core.signup.params.BusinessSignUpRawParams
import pimonitor.core.signup.params.IndividualSignUpRawParams
import validation.validate
import kotlin.js.JsExport
import kotlin.js.JsName
import kotlin.jvm.JvmName

@JsExport
interface SignUpService {
    @JsName("_ignore_signUpAsIndividual")
    fun signUpAsIndividual(rb: RequestBody.UnAuthorized<IndividualSignUpRawParams>): Later<SignUpResult>

    @JsName("_ignore_signUpAsBusiness")
    fun signUpAsBusiness(rb: RequestBody.UnAuthorized<BusinessSignUpRawParams>): Later<SignUpResult>
}