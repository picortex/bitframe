package pimonitor.authentication.signup

import pimonitor.MonitorParams
import kotlin.js.JsExport

@JsExport
sealed class SignUpIntent {
    object SelectRegistrationType : SignUpIntent()
    data class RegisterAsIndividual(val params: NameEmailFormParams?) : SignUpIntent()
    data class RegisterAsOrganization(val params: NameEmailFormParams?) : SignUpIntent()
    data class SubmitForm(val params: MonitorParams) : SignUpIntent()
}