@file:JsExport

package bitframe.authentication.signin

import bitframe.presenters.feedbacks.FormFeedback
import kotlin.js.JsExport

data class SignInState(
    val fields: SignInFormFields,
    val status: FormFeedback?
)