@file:JsExport

package pimonitor.authentication.signup

import kotlin.js.JsExport

sealed class SignUpState {

    data class Loading(val message: String) : SignUpState()

    object SelectRegistrationType : SignUpState()

    data class IndividualForm(
        val fields: IndividualFormFields,
        val organisationForm: OrganisationForm?
    ) : SignUpState()

    data class OrganisationForm(val fields: OrganisationFormFields) : SignUpState()

    data class Success(val message: String) : SignUpState()

    data class Failure(val cause: Throwable, val message: String? = cause.message) : SignUpState()
}