package pimonitor.authentication.signup

import bitframe.presenters.ButtonInputField
import bitframe.presenters.TextInputField
import contacts.Email
import pimonitor.Monitor
import pimonitor.MonitorBusinessParams
import kotlin.js.JsExport

@JsExport
data class OrganisationFormFields(
    val title: String = "Enter your organisation's information",
    val name: TextInputField = TextInputField(
        label = "Organisation's Name",
        hint = "John Doe Enterprises"
    ),
    val email: TextInputField = TextInputField(
        label = "Organisation's Email",
        hint = "support@enterpries.com"
    ),
    val nextButton: ButtonInputField = ButtonInputField("Submit"),
    val prevButton: ButtonInputField = ButtonInputField("Back")
) {
    internal fun copy(params: MonitorBusinessParams) = copy(
        name = name.copy(value = params.name),
        email = email.copy(value = params.email)
    )

    internal fun toParams() = MonitorBusinessParams(
        name = name.value,
        email = email.value
    )
}