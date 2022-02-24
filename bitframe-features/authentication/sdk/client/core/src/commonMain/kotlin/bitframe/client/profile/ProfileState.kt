package bitframe.client.profile

import presenters.feedbacks.Feedback
import kotlin.js.JsExport

@JsExport
data class ProfileState(
    val status: Feedback?
) {
    companion object {
        val INITIAL = ProfileState(
            status = Feedback.Loading("Initializing your profile, please wait . . .")
        )
    }
}