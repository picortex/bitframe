package bitframe.client.password

import bitframe.core.profile.params.RawChangePasswordParams
import presenters.feedbacks.Feedback
import presenters.fields.PasswordInputField
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
data class ChangePasswordState(
    val fields: Fields = Fields(),
    val status: Feedback? = null
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

    @JsName("copyRawParams")
    fun copy(params: RawChangePasswordParams, status: Feedback? = this.status) = copy(
        fields = fields.copy(
            previous = fields.previous.copy(value = params.previous),
            current = fields.current.copy(value = params.current)
        ),
        status = status
    )
}