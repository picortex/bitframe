@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.signup.params

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface BusinessSignUpRawParams : SignUpRawParams {
    val businessName: String
    val individualName: String
    val individualEmail: String
    val password: String
}

fun BusinessSignUpRawParams.toBusinessSignUpParams() = BusinessSignUpParams(
    businessName = requiredNotBlank(::businessName),
    individualName = requiredNotBlank(::individualName),
    individualEmail = requiredNotBlank(::individualEmail),
    password = requiredNotBlank(::password),
)