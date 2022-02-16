@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.signup

import pimonitor.client.signup.fields.BusinessFormFields
import pimonitor.client.signup.fields.IndividualFormFields
import presenters.feedbacks.Feedback
import presenters.fields.ButtonInputField
import presenters.fields.DropDownInputField
import presenters.fields.DropDownInputField.Option
import kotlin.js.JsExport

sealed class SignUpState(val select: DropDownInputField, open val status: Feedback?) {

    companion object {
        val REGISTER_AS_BUSINESS = Option("Register as Business", "Business")
        val REGISTER_AS_INDIVIDUAL = Option("Register as Individual", "Individual")
    }

    val title: String = "Create Your Account"

    data class IndividualForm(
        val fields: IndividualFormFields, override val status: Feedback?
    ) : SignUpState(fields.select, status)

    data class BusinessForm(
        val fields: BusinessFormFields, override val status: Feedback?
    ) : SignUpState(fields.select, status)

    val submitButton: ButtonInputField = ButtonInputField("Get Started")
}