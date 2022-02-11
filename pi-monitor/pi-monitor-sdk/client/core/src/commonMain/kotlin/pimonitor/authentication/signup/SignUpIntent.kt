@file:JsExport

package pimonitor.authentication.signup

import kotlin.js.JsExport

sealed class SignUpIntent {

    /**
     * type = [SignUpState.REGISTER_AS_BUSINESS] | [SignUpState.REGISTER_AS_INDIVIDUAL]
     */
    data class ChangeRegistrationType(val type: SignUpType) : SignUpIntent()

    sealed class Submit : SignUpIntent() {
        data class IndividualForm(val params: IRawIndividualSignUpParams) : Submit()
        data class BusinessForm(val params: IRawBusinessSignUpParams) : Submit()
    }
}