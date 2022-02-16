package bitframe.client.password

import presenters.feedbacks.Feedback
import presenters.fields.PasswordInputField
import kotlin.js.JsExport

@JsExport
data class ChangePasswordState(
    val fields: Fields = Fields(),
    val state: Feedback? = null
) {
    data class Fields(
        val previous: PasswordInputField = PasswordInputField(
            name = "previous",
            label = "Existing Password",
            hint = ""
        ),
        val current: PasswordInputField = PasswordInputField(
            name = "previous",
            label = "New Password",
            hint = ""
        )
    )
}