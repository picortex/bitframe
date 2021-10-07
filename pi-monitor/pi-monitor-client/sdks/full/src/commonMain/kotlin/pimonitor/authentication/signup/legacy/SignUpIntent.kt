@file:JsExport

package pimonitor.authentication.signup.legacy

import pimonitor.authentication.signup.IndividualRegistrationParams
import pimonitor.authentication.signup.MonitorBusinessParams
import kotlin.js.JsExport

sealed class SignUpIntent {
    object SelectRegistrationType : SignUpIntent()
    data class RegisterAsIndividual(val fields: IndividualFormFields?) : SignUpIntent()
    data class RegisterAsOrganization(val fields: OrganisationFormFields?) : SignUpIntent()
    data class SubmitIndividualForm(val params: IndividualRegistrationParams) : SignUpIntent()
    data class SubmitBusinessForm(val params: MonitorBusinessParams) : SignUpIntent()
}