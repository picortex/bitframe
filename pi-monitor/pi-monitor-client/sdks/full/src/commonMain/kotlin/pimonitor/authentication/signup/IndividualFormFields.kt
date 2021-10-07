@file:JsExport

package pimonitor.authentication.signup

import bitframe.presenters.fields.ButtonInputField
import bitframe.presenters.fields.TextInputField
import kotlin.js.JsExport

data class IndividualFormFields(
    val title: String = "Enter your personal information",
    val name: TextInputField = TextInputField(
        label = "Name",
        hint = "John Doe"
    ),
    val email: TextInputField = TextInputField(
        label = "Email",
        hint = "john@doe.com"
    ),
    val password: TextInputField = TextInputField(
        label = "Password",
        hint = "secure-password"
    ),
    val nextButton: ButtonInputField = ButtonInputField("Submit"),
    val prevButton: ButtonInputField = ButtonInputField("Back")
) {
    internal fun copy(params: IndividualRegistrationParams) = copy(
        name = name.copy(value = params.name),
        email = email.copy(value = params.email),
        password = email.copy(value = params.password)
    )
}