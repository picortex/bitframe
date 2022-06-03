@file:JsExport
@file:Suppress("NON_EXPORTABLE_TYPE")

package pimonitor.client.signup.fields

import pimonitor.client.signup.SignUpState.Companion.REGISTER_AS_BUSINESS
import pimonitor.client.signup.SignUpState.Companion.REGISTER_AS_INDIVIDUAL
import presenters.fields.*
import kotlin.js.JsExport

data class IndividualFormFields(
    val select: DropDownInputField = DropDownInputField(
        name = "registerAs",
        label = "Register As",
        REGISTER_AS_BUSINESS,
        REGISTER_AS_INDIVIDUAL.copy(selected = true)
    ),
    val name: TextInputField = TextInputField(
        name = "name", label = "Name", hint = "Full Name"
    ),
    val email: EmailInputField = EmailInputField(
        name = "email", label = "Email", hint = "Email Address"
    ),
    val password: PasswordInputField = PasswordInputField(
        name = "password", label = "Password"
    ),
    val submitButton: ButtonInputField = ButtonInputField(
        name = "submitButton", text = "Get Started"
    ),
)