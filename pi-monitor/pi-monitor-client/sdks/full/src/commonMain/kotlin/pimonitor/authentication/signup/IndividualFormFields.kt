@file:JsExport

package pimonitor.authentication.signup

import bitframe.presenters.fields.ButtonInputField
import bitframe.presenters.fields.DropDownInputField
import bitframe.presenters.fields.DropDownInputField.Option
import bitframe.presenters.fields.TextInputField
import kotlin.js.JsExport

data class IndividualFormFields(
    val select: DropDownInputField = DropDownInputField(
        Option("Select account type"),
        Option("Register as Business"),
        Option("Register as Individual", selected = true)
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