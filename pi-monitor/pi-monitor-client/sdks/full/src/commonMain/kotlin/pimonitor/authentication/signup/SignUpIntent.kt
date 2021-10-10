@file:JsExport

package pimonitor.authentication.signup

import pimonitor.monitors.SignUpParams
import kotlin.js.JsExport

sealed class SignUpIntent {
    object SelectRegisterAsIndividual : SignUpIntent()
    object SelectRegisterAsBusiness : SignUpIntent()

    sealed class Submit(open val params: SignUpParams) : SignUpIntent() {
        data class IndividualForm(override val params: SignUpParams.Individual) : Submit(params)
        data class BusinessForm(override val params: SignUpParams.Business) : Submit(params)
    }
}