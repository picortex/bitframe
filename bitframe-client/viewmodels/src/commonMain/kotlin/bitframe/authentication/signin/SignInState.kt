@file:JsExport

package bitframe.authentication.signin

import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import bitframe.presenters.feedbacks.FormFeedback
import kotlin.js.JsExport

sealed class SignInState {
    data class Form(
        val fields: SignInFormFields,
        val status: FormFeedback?
    ) : SignInState()

    data class Conundrum(
        val user: User,
        val spaces: List<Space>
    ) : SignInState()
}