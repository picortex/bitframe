@file:JsExport

package pimonitor.authentication.signup

import bitframe.presenters.feedbacks.FormFeedback
import bitframe.presenters.fields.ButtonInputField
import bitframe.presenters.fields.DropDownInputField
import bitframe.presenters.fields.DropDownInputField.Option
import kotlin.js.JsExport

sealed class SignUpState(val select: DropDownInputField, open val status: FormFeedback?) {

    companion object {
        val REGISTER_AS_BUSINESS = Option("Register as Business", "Business")
        val REGISTER_AS_INDIVIDUAL = Option("Register as Individual", "Individual")
    }

    val title: String = "Create Your Account"

    data class IndividualForm(
        val fields: IndividualFormFields, override val status: FormFeedback?
    ) : SignUpState(fields.select, status)

    data class BusinessForm(
        val fields: BusinessFormFields, override val status: FormFeedback?
    ) : SignUpState(fields.select, status)

    val submitButton: ButtonInputField = ButtonInputField("Get Started")
}