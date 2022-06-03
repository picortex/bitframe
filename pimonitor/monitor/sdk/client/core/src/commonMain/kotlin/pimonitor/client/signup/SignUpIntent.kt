@file:JsExport

package pimonitor.client.signup

import pimonitor.core.signup.params.SignUpBusinessRawParams
import pimonitor.core.signup.params.SignUpIndividualRawParams
import kotlin.js.JsExport

sealed class SignUpIntent {

    /**
     * type = [SignUpState.REGISTER_AS_BUSINESS] | [SignUpState.REGISTER_AS_INDIVIDUAL]
     */
    data class ChangeRegistrationType(val type: SignUpType) : SignUpIntent()

    sealed class Submit : SignUpIntent() {
        data class IndividualForm(val params: SignUpIndividualRawParams) : Submit()
        data class BusinessForm(val params: SignUpBusinessRawParams) : Submit()
    }
}