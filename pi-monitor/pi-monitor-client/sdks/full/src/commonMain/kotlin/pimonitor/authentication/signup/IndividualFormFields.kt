package pimonitor.authentication.signup

import bitframe.presenters.ButtonInputField
import bitframe.presenters.TextInputField
import pimonitor.MonitorPersonParams
import kotlin.js.JsExport

@JsExport
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
    internal fun copy(params: MonitorPersonParams) = copy(
        name = name.copy(value = params.name),
        email = email.copy(value = params.email),
        password = email.copy(value = params.password)
    )
}