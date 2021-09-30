@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.authentication.signup.exports

import pimonitor.authentication.signup.SignUpService

open class SignUpServiceWrapper(service: SignUpService) {
    val registerIndividual = { params: IndividualParams ->
        service.registerIndividuallyAs(params.toIndividualRegistrationParams())
    }
}