@file:JsExport

package pimonitor.client.signup.fields

import presenters.fields.ButtonInputField
import presenters.fields.DropDownInputField
import presenters.fields.TextInputField
import pimonitor.client.signup.SignUpState.Companion.REGISTER_AS_BUSINESS
import pimonitor.client.signup.SignUpState.Companion.REGISTER_AS_INDIVIDUAL
import kotlin.js.JsExport

data class IndividualFormFields(
    val select: DropDownInputField = DropDownInputField(
        REGISTER_AS_BUSINESS,
        REGISTER_AS_INDIVIDUAL.copy(selected = true)
    ),
    val name: TextInputField = TextInputField(
        label = "Name", hint = "Full Name"
    ),
    val email: TextInputField = TextInputField(
        label = "Email", hint = "Email Address"
    ),
    val password: TextInputField = TextInputField("Password"),
    val submitButton: ButtonInputField = ButtonInputField("Get Started"),
)