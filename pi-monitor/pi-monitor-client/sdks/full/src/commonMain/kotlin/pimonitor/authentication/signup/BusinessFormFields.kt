@file:JsExport

package pimonitor.authentication.signup

import bitframe.presenters.fields.ButtonInputField
import bitframe.presenters.fields.DropDownInputField
import bitframe.presenters.fields.DropDownInputField.Option
import bitframe.presenters.fields.TextInputField
import kotlin.js.JsExport

data class BusinessFormFields(
    val title: String = "Create Your Account",
    val select: DropDownInputField = DropDownInputField(
        Option("Register as Business", selected = true),
        Option("Register as Individual")
    ),
    val businessName: TextInputField = TextInputField("Business Name"),
    val individualName: TextInputField = TextInputField("Your Name"),
    val individualEmail: TextInputField = TextInputField("Your Email"),
    val password: TextInputField = TextInputField("Password"),
    val submitButton: ButtonInputField = ButtonInputField("Get Started"),
)