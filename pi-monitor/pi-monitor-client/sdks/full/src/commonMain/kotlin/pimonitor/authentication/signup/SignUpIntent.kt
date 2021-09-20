package pimonitor.authentication.signup

import pimonitor.MonitorBusinessParams
import pimonitor.MonitorParams
import pimonitor.MonitorPersonParams
import kotlin.js.JsExport

@JsExport
sealed class SignUpIntent {
    object SelectRegistrationType : SignUpIntent()
    data class RegisterAsIndividual(val fields: IndividualFormFields?) : SignUpIntent()
    data class RegisterAsOrganization(val fields: OrganisationFormFields?) : SignUpIntent()
    data class SubmitIndividualForm(val params: MonitorPersonParams) : SignUpIntent()
    data class SubmitBusinessForm(val params: MonitorBusinessParams) : SignUpIntent()
}