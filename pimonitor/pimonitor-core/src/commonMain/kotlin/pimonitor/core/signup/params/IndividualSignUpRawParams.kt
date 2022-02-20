@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.core.signup.params

import validation.requiredNotBlank
import kotlin.js.JsExport

@JsExport
interface IndividualSignUpRawParams : SignUpRawParams {
    val name: String
    val email: String
    val password: String

    fun toIndividualSignUpParams() = IndividualSignUpParams(
        name = requiredNotBlank(::name),
        email = requiredNotBlank(::email),
        password = requiredNotBlank(::password),
    )
}