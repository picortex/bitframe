@file:JsExport

package bitframe.authentication.signin

import bitframe.authentication.spaces.Space
import bitframe.authentication.users.User
import kotlinx.collections.interoperable.List
import presenters.feedbacks.FormFeedback
import presenters.feedbacks.FormFeedback.Failure
import presenters.feedbacks.FormFeedback.Success
import presenters.feedbacks.FormFeedback.Loading

import kotlin.js.JsExport

sealed class SignInState {
    data class Form(
        /**
         * All the fields and the info to display on the sign in form
         */
        val fields: SignInFormFields,
        /**
         * The status of a form whether it is [Loading], a [Success], or a [Failure].
         * if it is null, then there is no status to give and nothing is happening in the background
         */
        val status: FormFeedback?
    ) : SignInState()

    data class Conundrum(
        val user: User,
        val spaces: List<Space>
    ) : SignInState()
}