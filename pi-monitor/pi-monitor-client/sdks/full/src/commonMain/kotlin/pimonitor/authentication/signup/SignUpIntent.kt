@file:JsExport

package pimonitor.authentication.signup

import pimonitor.authentication.signup.legacy.IndividualFormFields
import pimonitor.authentication.signup.legacy.OrganisationFormFields
import pimonitor.monitors.SignUpParams
import kotlin.js.JsExport

sealed class SignUpIntent {
    data class SelectRegisterAsIndividual(val fields: IndividualFormFields?) : SignUpIntent()
    data class SelectRegisterAsBusiness(val fields: OrganisationFormFields?) : SignUpIntent()

    sealed class Submit(open val params: SignUpParams) : SignUpIntent() {
        data class IndividualForm(override val params: SignUpParams.Individual) : Submit(params)
        data class BusinessForm(override val params: SignUpParams.Business) : Submit(params)
    }
}