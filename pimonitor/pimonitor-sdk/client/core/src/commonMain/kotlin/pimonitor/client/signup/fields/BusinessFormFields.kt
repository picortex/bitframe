@file:JsExport

package pimonitor.client.signup.fields

import pimonitor.client.signup.SignUpState.Companion.REGISTER_AS_BUSINESS
import pimonitor.client.signup.SignUpState.Companion.REGISTER_AS_INDIVIDUAL
import presenters.fields.*
import kotlin.js.JsExport

data class BusinessFormFields(
    val title: String = "Create Your Account",
    val select: DropDownInputField = DropDownInputField(
        name = "registerAs",
        REGISTER_AS_BUSINESS.copy(selected = true),
        REGISTER_AS_INDIVIDUAL
    ),
    val businessName: TextInputField = TextInputField(
        name = "businessName", label = "Business Name"
    ),
    val individualName: TextInputField = TextInputField(
        name = "individualName", label = "Your Name"
    ),
    val individualEmail: EmailInputField = EmailInputField(
        name = "individualEmail", label = "Your Email"
    ),
    val password: PasswordInputField = PasswordInputField(
        name = "password", label = "Password"
    ),
    val submitButton: ButtonInputField = ButtonInputField(
        name = "submitButton", text = "Get Started"
    ),
)