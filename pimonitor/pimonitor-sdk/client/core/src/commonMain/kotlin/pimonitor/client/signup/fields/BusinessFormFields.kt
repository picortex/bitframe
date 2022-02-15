@file:JsExport

package pimonitor.client.signup.fields

import presenters.fields.ButtonInputField
import presenters.fields.DropDownInputField
import presenters.fields.TextInputField
import pimonitor.client.signup.SignUpState.Companion.REGISTER_AS_BUSINESS
import pimonitor.client.signup.SignUpState.Companion.REGISTER_AS_INDIVIDUAL
import kotlin.js.JsExport

data class BusinessFormFields(
    val title: String = "Create Your Account",
    val select: DropDownInputField = DropDownInputField(
        REGISTER_AS_BUSINESS.copy(selected = true),
        REGISTER_AS_INDIVIDUAL
    ),
    val businessName: TextInputField = TextInputField("Business Name"),
    val individualName: TextInputField = TextInputField("Your Name"),
    val individualEmail: TextInputField = TextInputField("Your Email"),
    val password: TextInputField = TextInputField("Password"),
    val submitButton: ButtonInputField = ButtonInputField("Get Started"),
)