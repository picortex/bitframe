@file:JsExport

package pimonitor.authentication.signup

import bitframe.presenters.feedbacks.FormFeedback
import kotlin.js.JsExport

sealed class SignUpState {

    data class IndividualForm(
        val fields: IndividualFormFields, val status: FormFeedback?
    ) : SignUpState()

    data class BusinessForm(
        val fields: BusinessFormFields, val status: FormFeedback?
    ) : SignUpState()
}