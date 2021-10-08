@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.authentication.signup.exports

import pimonitor.authentication.signup.SignUpService

open class SignUpServiceWrapper(service: SignUpService) {
    val registerIndividual = { params: RegisterIndividualParams ->
        service.signUp(params.toSignUpParams())
    }

    val registerBusiness = { params: RegisterBusinessParams ->
        service.signUp(params.toSignUpParams())
    }
}