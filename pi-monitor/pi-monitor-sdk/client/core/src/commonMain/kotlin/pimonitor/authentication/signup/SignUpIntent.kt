@file:JsExport

package pimonitor.authentication.signup

import pimonitor.authentication.signup.SignUpParams
import kotlin.js.JsExport

sealed class SignUpIntent {
    @Deprecated("In favor of ChangeRegistrationType")
    object SelectRegisterAsIndividual : SignUpIntent()

    @Deprecated("In favor of ChangeRegistrationType")
    object SelectRegisterAsBusiness : SignUpIntent()

    /**
     * type = [SignUpState.REGISTER_AS_BUSINESS] | [SignUpState.REGISTER_AS_INDIVIDUAL]
     */
    data class ChangeRegistrationType(val type: String) : SignUpIntent()

    sealed class Submit(open val params: SignUpParams) : SignUpIntent() {
        data class IndividualForm(override val params: SignUpParams.Individual) : Submit(params)
        data class BusinessForm(override val params: SignUpParams.Business) : Submit(params)
    }
}